package com.dm4nk.multithreading.demo.scopedvalue;

// --enable-preview
public class ScopedValueExample {

    public static ScopedValue<String> USERNAME = ScopedValue.newInstance();

    public static void main(String[] args) throws Exception {
        String result = controllerProcess("body", "FRAUD");
        System.out.println(result);
    }

    public static String controllerProcess(String body, String username) throws Exception {
        return ScopedValue.callWhere(USERNAME, username, () -> serviceProcess(body));
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
