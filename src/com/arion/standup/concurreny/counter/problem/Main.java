package com.arion.standup.concurreny.counter.problem;

public class Main {
    private int count = 0;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Thread thread1 = new Task();
        thread1.setName("thread-1");
        Thread thread2 = new Task();
        thread2.setName("thread-2");
        thread1.start();
        thread2.start();

    }

    public class Task extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000000; i++) {
                count ++;
            }
            System.out.println(Thread.currentThread().getName() + ": " + count);
        }
    }
}
