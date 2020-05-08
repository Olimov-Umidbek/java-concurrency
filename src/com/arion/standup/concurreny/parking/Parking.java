package com.arion.standup.concurreny.parking;

import java.util.concurrent.Semaphore;

/**
 * This problem about parking.
 * https://habr.com/ru/post/277669/
 */
public class Parking {

    private static final boolean[] PARKING_PLACES = new boolean[5];
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            System.out.printf("№%d avtomobil parkovkaga keldi. \n", carNumber);

            try {
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < 5; i++) {
                        if (!PARKING_PLACES[i]) {
                            PARKING_PLACES[i] = true;
                            parkingNumber = i;

                            System.out.printf("№%d avtomobil parkovkaning №%d joyiga joylashdi.\n", carNumber, i);
                            break;
                        }
                    }
                }

                Thread.sleep(5000);

                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingNumber] = false;
                }

                SEMAPHORE.release();
                System.out.printf("№%d avtomobil parkovkadan chiqdi.\n", carNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
