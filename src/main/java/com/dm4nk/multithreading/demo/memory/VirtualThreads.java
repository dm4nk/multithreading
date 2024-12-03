package com.dm4nk.multithreading.demo.memory;

// -XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics
public class VirtualThreads {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; ++i) {
            Thread.ofVirtual().start(
                    () -> {
                        try {
                            Thread.sleep(100_000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
        System.exit(0);
    }
}

// virtual:

//Total: reserved=10209581416, committed=641468776
//                     Thread (reserved=36808024, committed=2020696)
//                            (threads #34)
//                            (stack: reserved=36700160, committed=1912832, peak=1912832)
//                            (malloc=68936 #239) (peak=77472 #243)
//                            (arena=38928 #70) (peak=123264 #45)

//Total: reserved=10213797107, committed=642391283
//                     Thread (reserved=47323296, committed=2746528)
//                            (threads #44)
//                            (stack: reserved=47185920, committed=2609152, peak=2609152)
//                            (malloc=88416 #300) (peak=97032 #304)
//                            (arena=48960 #88) (peak=113552 #84)

//Total: reserved=10234212031, committed=647585471
//                     Thread (reserved=50507040, committed=2825504)
//                            (threads #47)
//                            (stack: reserved=50331648, committed=2650112, peak=2650112)
//                            (malloc=92072 #318) (peak=104808 #336)
//                            (arena=83320 #92) (peak=151176 #96)

// итого

// 10000 -> 58kb per thred, 1000 -> 60kb per thred, 58kb per thread, хотя так неправильно считать, т.к.
// виртуальные потоки это просто объекты
