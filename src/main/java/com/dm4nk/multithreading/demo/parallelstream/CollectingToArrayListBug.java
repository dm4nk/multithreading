package com.dm4nk.multithreading.demo.parallelstream;

import com.dm4nk.multithreading.other.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class CollectingToArrayListBug {

    public static void main(String[] args) {
        int n = 10000;
        List<Integer> intList = Utils.generateListOfRandomNumbers(n);

        List<Integer> resultingList = new ArrayList<>();
        intList.forEach(number -> {
            if (number % 2 == 0) {
                resultingList.add(number / 2);
                resultingList.add(number);
            } else {
                resultingList.add(number * 3);
            }
        });

        // должно получиться 1.5n элементов
        System.out.println(resultingList.size());
    }
}
