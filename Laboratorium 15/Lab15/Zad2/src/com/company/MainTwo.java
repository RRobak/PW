package com.company;

import java.util.concurrent.*;
import java.util.*;

public class MainTwo extends Thread{

    private static final Random rand = new Random(30);
    private final int docNumber;

    MainTwo(int x){
        this.docNumber = x;
    }

    @Override
    public void run(){
        printDocument(docNumber);
    }

    public static synchronized void printDocument(final int x) {

        System.out.println( "Drukarka drukuje dokument nr: " + x );
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(1000));
        }catch (InterruptedException e) {
            System.out.println("Przerwanie zadania w sleep()");
        }
        System.out.println( "Drukarka wydrukowała  dokument nr: " + x );
    }

    public static void main(String[] args) {
        System.out.println("Zadanie 2B - wersja 2");

        ExecutorService ex = Executors.newCachedThreadPool();

        for (int i = 1; i <= 5; i++) {
            ex.submit(new MainTwo(i));
        }

        ex.shutdown();
    }
}


