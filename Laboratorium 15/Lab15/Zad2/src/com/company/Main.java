package com.company;

import java.util.concurrent.*;
import java.util.*;

public class Main {

    private final ExecutorService ex = Executors.newSingleThreadExecutor();
    private final Random los = new Random(30);

    public Future<Integer> printDocument(final int num) {

        return ex.submit(() -> {
            System.out.println( "Rozpoczęto drukowanie nr: " + num );
            try {
                TimeUnit.MILLISECONDS.sleep(100 + los.nextInt(1000));
            }catch (InterruptedException e) {
                System.out.println("Przerwanie zadania w sleep()");
            }
            System.out.println( "Zakonczono nr: " + num );
            return num;
        });
    }
    public void shutdown() {
        ex.shutdown();
    }

    public static void main(String[] args) {

        Main d1 = new Main();
        List<Future<?>> results = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 5; i++) {
            results.add(d1.printDocument(i));
        }

        while (results.size() > 0) {
            results.removeIf(Future::isDone);
        }
        d1.shutdown();
    }


}


