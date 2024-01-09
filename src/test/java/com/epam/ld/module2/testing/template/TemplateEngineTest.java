package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TemplateEngineTest {

    @Mock
    private Client client;

    @Mock
    private Template template;

    @Test
    @DisplayName("The system replaces variable placeholders like #{subject} from a template with values provided at runtime.")
    public void generateMessagePlaceholdersAreReplaced(){
        TemplateEngine templateEngine = new TemplateEngine();
        Map<String, String> variables = new HashMap<>();
        variables.put("subject", "hello!");
        variables.put("body", "Merry Christmas");

        when(client.getVariables()).thenReturn(variables);
        when(template.getTemplateString()).thenReturn("#{subject} - #{body}");
        String actual = assertDoesNotThrow(() -> templateEngine.generateMessage(template, client));
        String expected = "hello! - Merry Christmas";
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("The Template Engine throws an exception if at least one placeholder value is missed")
    public void exceptionIsThrownIfPlaceholderValueMissed(){
        TemplateEngine templateEngine = new TemplateEngine();
        Map<String, String> variables = new HashMap<>();
        variables.put("subject", "hello!");
//        body parameter is missing

        when(client.getVariables()).thenReturn(variables);
        when(template.getTemplateString()).thenReturn("#{subject} - #{body}");

        assertThrows(IllegalArgumentException.class,
                ()-> templateEngine.generateMessage(template, client)
        );
    }

    @Test
    @DisplayName("The Template Engine ignores extra variable placeholders from a template.")
    public void extraMessagePlaceholdersAreIgnored() {
        TemplateEngine templateEngine = new TemplateEngine();
        Map<String, String> variables = new HashMap<>();
        variables.put("subject", "hello!");
        variables.put("body", "Merry Christmas");
        variables.put("topic", "Happy New Year");

        when(client.getVariables()).thenReturn(variables);
        when(template.getTemplateString()).thenReturn("#{subject} - #{body}");
        String expected = "hello! - Merry Christmas";
        String actual = assertDoesNotThrow(() -> templateEngine.generateMessage(template, client));
        assertEquals(expected, actual);
    }
}
