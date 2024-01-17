package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.ClientFile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
public class ClientFileTest {

    static List<String> fileNames = new ArrayList<>();
    static String notEmptyFileName = "test_input.csv";
    static String notEmptyFileContent = "subject,subj\nbody,bod";
    static String emptyFileName = "test_input_empty.csv";
    static String emptyFileContent = "";

    static Map<String,String> expectedVariables = new HashMap<>(){{
        put("subject","subj");
        put("body","bod");
    }};

    @BeforeAll
    public static void createTestFiles() throws IOException {

        File notEmptyFile = new File(notEmptyFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(notEmptyFile));
        writer.write(notEmptyFileContent);
        writer.close();

        File emptyFile = new File(emptyFileName);
        writer = new BufferedWriter(new FileWriter(emptyFile));
        writer.write(emptyFileContent);
        writer.close();

        fileNames.add(notEmptyFileName);
        fileNames.add(emptyFileName);
    }

    @AfterAll
    public static void createFiles(){
        for(String file : fileNames){
            File fileToDelete = new File(file);
            fileToDelete.delete();
        }
    }

    static Stream<Arguments> args(){
        return Stream.of(
                arguments(notEmptyFileName, expectedVariables),
                arguments(emptyFileName, new HashMap<String, String>())
        );
    }

    @ParameterizedTest
    @MethodSource("args")
    public void valuesReturnedFromValidFile(String filename, Map<String, String> vars){
        ClientFile clientFile = new ClientFile(filename);
        assertEquals(vars, clientFile.getVariables());
    }
}
