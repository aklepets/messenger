package com.epam.ld.module2.testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClientFile extends Client{
    String fileName;
    public ClientFile(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, String> getVariables(){
        Map<String, String> variables = new HashMap<>();

        String line = "";
        String csvSplitBy = ",";

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while((line = br.readLine())!=null){
                String[] data = line.split(csvSplitBy);
                if(data.length<2){
                    System.err.println("Invalid line: " +line);
                    continue;
                }
                String key = data[0];
                String value = data[1];
                variables.put(key, value);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return variables;
    }
}
