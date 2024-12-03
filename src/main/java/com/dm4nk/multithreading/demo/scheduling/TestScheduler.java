package com.dm4nk.multithreading.demo.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class TestScheduler {

    @Scheduled(fixedRate = 1000)
    void executeTask1() throws InterruptedException {
        log("Executing Task 1");
        Thread.sleep(5000);
    }

    @Scheduled(fixedRate = 1000)
    void executeTask2() throws InterruptedException {
        log("Executing Task 2");
        Thread.sleep(1000);
    }

    // https://www.uptimia.com/cron-expression-generator
//    @Scheduled(cron = "*/3 * * * * 1-5")
//    void croneTask() throws InterruptedException {
//        log("Executing crone");
//        Thread.sleep(1000);
//    }

    private static void log(String msg) {
        System.out.println(String.format("[%s:%s] %s",
                        Thread.currentThread().getName(),
                        Thread.currentThread().isVirtual() ? "Virtual" : "Platform",
                        msg
                )
        );
    }
}

// Virtual threads disabled, pool size: 1:

//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 1
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 1
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 1
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 1
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 1
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 1
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 1

// Virtual threads disabled, pool size: 2:

//[scheduling-1:Platform] Executing Task 2
//[scheduling-2:Platform] Executing Task 1
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 2
//[scheduling-2:Platform] Executing Task 1
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 2
//[scheduling-1:Platform] Executing Task 2
//[scheduling-2:Platform] Executing Task 1

// Virtual threads enabled, pool size: 1:

//[scheduling-3:Virtual] Executing Task 2
//[scheduling-2:Virtual] Executing Task 1
//[scheduling-5:Virtual] Executing Task 2
//[scheduling-4:Virtual] Executing Task 1
//[scheduling-6:Virtual] Executing Task 1
//[scheduling-7:Virtual] Executing Task 2
//[scheduling-8:Virtual] Executing Task 1
//[scheduling-9:Virtual] Executing Task 2
//[scheduling-10:Virtual] Executing Task 1
//[scheduling-11:Virtual] Executing Task 2
//[scheduling-12:Virtual] Executing Task 1
//[scheduling-13:Virtual] Executing Task 2
//[scheduling-15:Virtual] Executing Task 2
//[scheduling-14:Virtual] Executing Task 1
//[scheduling-17:Virtual] Executing Task 2
//[scheduling-16:Virtual] Executing Task 1
//[scheduling-18:Virtual] Executing Task 1
//[scheduling-19:Virtual] Executing Task 2
//[scheduling-20:Virtual] Executing Task 1
