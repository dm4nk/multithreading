package com.dm4nk.multithreading.demo.parallelstream;

import com.dm4nk.multithreading.other.util.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ParallelStreamController {

    @GetMapping("/parallelstream")
    public String test() {
        log("Message from controller");
        int n = 20;
        List<Integer> intList = Utils.generateListOfRandomNumbers(n);

        List<Integer> list = intList.parallelStream()
                .map(number -> {
                    log("Message from parallel stream");
                    return number * 2;
                })
                .toList();

        return "OK";
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


//[tomcat-handler-0:Virtual] Message from controller
//[tomcat-handler-0:Virtual] Message from parallel stream
//[tomcat-handler-0:Virtual] Message from parallel stream
//[tomcat-handler-0:Virtual] Message from parallel stream
//[tomcat-handler-0:Virtual] Message from parallel stream
//[tomcat-handler-0:Virtual] Message from parallel stream
//[ForkJoinPool.commonPool-worker-3:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-3:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-2:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-2:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-5:Platform] Message from parallel stream
//[tomcat-handler-0:Virtual] Message from parallel stream
//[ForkJoinPool.commonPool-worker-5:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-3:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-6:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-5:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-1:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-7:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-5:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-4:Platform] Message from parallel stream
//[ForkJoinPool.commonPool-worker-2:Platform] Message from parallel stream
