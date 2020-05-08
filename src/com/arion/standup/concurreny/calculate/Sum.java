package com.arion.standup.concurreny.calculate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Sum {

    private static List<Long> list = new ArrayList<>();

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            list.add((long) random.nextInt(10000000));
        }

        sumWithAtomic();

        sumWithArray();
    }

    public static void sumWithAtomic() {
        AtomicReference<Long> sum = new AtomicReference<>(0L);

        list.forEach(aLong -> sum.updateAndGet(v -> v + aLong));

        System.out.println("Atomic result: " + sum.get());
    }

    public static void sumWithArray() {
        final Long[] sum = {0L};

        list.forEach(aLong -> {
            sum[0] += aLong;
        });

        System.out.println("Result: " + sum[0]);
    }
}
