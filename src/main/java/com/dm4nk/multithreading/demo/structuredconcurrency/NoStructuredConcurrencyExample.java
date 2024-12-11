package com.dm4nk.multithreading.demo.structuredconcurrency;

import java.util.concurrent.CompletableFuture;

public class NoStructuredConcurrencyExample {

    public static CompletableFuture<String> serviceCall1 = CompletableFuture.supplyAsync(() -> {
        System.out.println("Run Task 1");
        wait1sec();
        throw new RuntimeException();
    });

    public static CompletableFuture<String> serviceCall2 = CompletableFuture.supplyAsync(() -> {
        System.out.println("Run Task 2");
        wait5sec();
        return "World";
    });

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> completableFuture = CompletableFuture.allOf(
                serviceCall1,
                serviceCall2
        ).thenApply(_ -> serviceCall1.join() + serviceCall2.join());

        System.out.println(completableFuture.get());
    }

    public static void wait1sec() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void wait5sec() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
