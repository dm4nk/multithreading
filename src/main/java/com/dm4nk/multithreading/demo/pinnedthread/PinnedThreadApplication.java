package com.dm4nk.multithreading.demo.pinnedthread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PinnedThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(PinnedThreadApplication.class, args);
    }
}
