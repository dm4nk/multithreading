package com.dm4nk.multithreading.demo.pinnedthread;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@RequestMapping("/api/v1")
@RestController
public class PinnedThreadController {

    private final Object obj = new Object();

    @GetMapping("/pinthread1")
    public String pinthread1() {
        int iterations = 3;

        for (int i = 0; i < iterations; i++) {
            synchronized (obj) {
                // non-blocking code or fast code
                log("hi");
            }
        }

        return "OK";
    }

    @GetMapping("/pinthread2")
    public String pinthread2() {
        int iterations = 3;

        for (int i = 0; i < iterations; i++) {
            synchronized (obj) {
                // blocking code
                blockingNetworkCall();
            }
        }

        return "OK";
    }

    @GetMapping("/pinthread3")
    public String pinthread3() {
        var lock = new ReentrantLock();

        int iterations = 3;

        for (int i = 0; i < iterations; i++) {
            lock.lock();
            try {
                // blocking code
                blockingNetworkCall();
            } finally {
                lock.unlock();
            }
        }

        return "OK";
    }

    Map<String, String> cache = new ConcurrentHashMap<>();

    @GetMapping("/pinthread4")
    public String pinthread4() {
        String cachedValue = cache.computeIfAbsent("foo", key -> {
            final var calculated = blockingNetworkCall();
            log("Caching value for '" + key + "': " + calculated);
            return calculated;
        });
        log("Cached value: " + cachedValue);

        return "OK";
    }

    private String blockingNetworkCall() {
        try {
            Thread.sleep(50);
            return "Value";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void log(String msg) {
        System.out.println(String.format("[%s:%s] %s",
                        Thread.currentThread().getName(),
                        Thread.currentThread().isVirtual() ? "Virtual" : "Platform",
                        msg
                )
        );
    }
}
