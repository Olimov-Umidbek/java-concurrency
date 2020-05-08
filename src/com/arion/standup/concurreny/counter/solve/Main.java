package com.arion.standup.concurreny.counter.solve;

import com.arion.standup.concurreny.counter.Counter;

public class Main {

    private Counter counter = new Counter();

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {

        Thread thread1 = new Task(counter);
        thread1.setName("task-1");
        Thread thread2 = new Task(counter);
        thread2.setName("task-2");
        thread1.start();
        thread2.start();

    }

    public class Task extends Thread {

        private Counter counter;

        public Task(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            synchronized (counter) {
                for (int i = 0; i < 10000000; i++) {
                    counter.increment();
                }
            }
            System.out.println(Thread.currentThread().getName() + ": " + counter.getCount());
        }
    }

}
