package com.dm4nk.multithreading.other.util;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.IntStream;

@UtilityClass
public class Utils {
    public static List<Integer> generateListOfRandomNumbers(int n) {
        return IntStream.range(0, n).boxed().toList();
    }
}
