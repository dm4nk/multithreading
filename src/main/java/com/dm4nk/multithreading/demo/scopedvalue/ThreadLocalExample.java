package com.dm4nk.multithreading.demo.scopedvalue;

public class ThreadLocalExample {

    public static ThreadLocal<String> USERNAME = new ThreadLocal<>();

    public static void main(String[] args) {
        String result = controllerProcess("body", "FRAUD");
        System.out.println(result);
    }

    public static String controllerProcess(String body, String username) {
        USERNAME.set(username);
        return serviceProcess(body);
    }

    private static String serviceProcess(String body) {
        return otherServiceProcess(body);
    }

    private static String otherServiceProcess(String body) {
        if ("FRAUD".equals(USERNAME.get())) {
            return "Fraud!";
        }

        return body;
    }
}
