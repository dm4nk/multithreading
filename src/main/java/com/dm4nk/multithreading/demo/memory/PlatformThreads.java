package com.dm4nk.multithreading.demo.memory;

// -XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics
public class PlatformThreads {

    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            Thread.ofPlatform().start(
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

// platform:

//Total: reserved=10297359144, committed=648076072
//                     Thread (reserved=139866688, committed=8356416)
//                            (threads #132)
//                            (stack: reserved=139460608, committed=7950336, peak=7950336)
//                            (malloc=248568 #933) (at peak)
//                            (arena=157512 #267) (peak=222968 #267)

//Total: reserved=11245463233, committed=711980737
//                     Thread (reserved=1086392928, committed=70789728)
//                            (threads #1033)
//                            (stack: reserved=1083179008, committed=67575808, peak=67575808)
//                            (malloc=1976408 #7289) (at peak)
//                            (arena=1237512 #2067) (peak=1302968 #2067)

//Total: reserved=20728269307, committed=1354713595
//                     Thread (reserved=10551660464, committed=691933104)
//                            (threads #10033)
//                            (stack: reserved=10520363008, committed=660635648, peak=660635648)
//                            (malloc=19256992 #70851) (peak=19341864 #70837)
//                            (arena=12040464 #20070) (peak=12073192 #20070)

// итого

// 10000 -> 67kb per thred, 1000 -> 67kb per thred, 62kb per thread
