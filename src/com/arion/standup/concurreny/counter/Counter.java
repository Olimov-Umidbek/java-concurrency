package com.arion.standup.concurreny.counter;

public class Counter {
    private volatile int count = 0;

    public void increment() {
        count ++;
    }

    public int getCount() {
        return count;
    }
}
