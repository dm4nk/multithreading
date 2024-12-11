package com.dm4nk.multithreading.demo.scopedvalue;

public class NoThreadLocalExample {

    public static void main(String[] args) {
        String result = controllerProcess("body", "FRAUD");
        System.out.println(result);
    }

    public static String controllerProcess(String body, String username) {
        return serviceProcess(body, username);
    }

    private static String serviceProcess(String body, String username) {
        return otherServiceProcess(body, username);
    }

    private static String otherServiceProcess(String body, String username) {
        if ("FRAUD".equals(username)) {
            return "Fraud!";
        }

        return body;
    }
}
