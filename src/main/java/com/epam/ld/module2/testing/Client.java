package com.epam.ld.module2.testing;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The type Client.
 */
public class Client {
    private String addresses;

    /**
     * Gets addresses.
     *
     * @return the addresses
     */
    public String getAddresses() {
        return addresses;
    }

    public Map<String, String> getVariables(){
        Map<String, String> variables = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        boolean enterParameters = true;
        while(enterParameters) {
            System.out.println("enter parameter name");
            String key = scanner.nextLine();
            System.out.println("enter parameter value");
            String value = scanner.nextLine();
            variables.put(key, value);
            System.out.println("want enter more parameters? [y, yes]");
            String yes = scanner.nextLine();
            enterParameters = (yes.contains("y"));
        }
        scanner.close();
        return variables;
    }
    /**
     * Sets addresses.
     *
     * @param addresses the addresses
     */
    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }
}
