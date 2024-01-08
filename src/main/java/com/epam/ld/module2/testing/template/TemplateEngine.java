package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) throws Exception {
        String templateString = template.getTemplateString();

        for (Map.Entry<String, String> entry : client.getVariables().entrySet()) {
            templateString = templateString.replace("#{"+entry.getKey()+"}", entry.getValue());
        }

        if(templateString.matches(".*#\\{\\w*\\}.*")) {
            throw new IllegalArgumentException("some parameters missing");
        }

        return templateString;
    }
}
