package com.dm4nk.multithreading.demo.parallelstream;

import com.dm4nk.multithreading.other.util.Utils;

import java.util.List;

public class CollectingToArrayListCorrect {

    public static void main(String[] args) {
        int n = 10000;
        List<Integer> intList = Utils.generateListOfRandomNumbers(n);

        List<Integer> resultingList = intList.parallelStream().<Integer>mapMulti((number, consumer) -> {
                    if (number % 2 == 0) {
                        consumer.accept(number / 2);
                        consumer.accept(number);
                    } else {
                        consumer.accept(number * 3);
                    }
                })
                .toList();

        // должно получиться 1.5n элементов
        System.out.println(resultingList.size());
    }
}
