package com.epam.ld.module2.testing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Mail server class.
 */
public class MailServer {

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        System.out.println("Addresses: " + addresses + ",\nMessage: " + messageContent);
    }

    public void send(String addresses, String messageContent, String fileName) {
        writeTextToFile(fileName, "Addresses: " + addresses + ",\nMessage: " + messageContent);
    }

    public static void writeTextToFile(String fileName, String text){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(text);
        }catch(IOException e){
            System.err.format("IOException: %s%n", e);
        }finally{
            try {
                if(writer != null){
                    writer.close();
                }
            } catch(IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }
}
