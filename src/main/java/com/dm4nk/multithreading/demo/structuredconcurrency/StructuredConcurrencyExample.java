package com.dm4nk.multithreading.demo.structuredconcurrency;


import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeoutException;

public class StructuredConcurrencyExample {

    public static Callable<String> subtask1 = () -> {
        System.out.println("Run Task 1");
        Thread.sleep(1000);
        throw new RuntimeException();
    };

    public static Callable<String> subtask2 = () -> {
        System.out.println("Run Task 2");
        Thread.sleep(5000);
        return "2";
    };

    public static void main(String[] args) throws InterruptedException, TimeoutException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            StructuredTaskScope.Subtask<String> task1 = scope.fork(subtask1);
            StructuredTaskScope.Subtask<String> task2 = scope.fork(subtask2);

            scope.join();
//            scope.joinUntil(Instant.now().plusMillis(100));

            System.out.println(task1.get() + task2.get());
        }
    }
}
