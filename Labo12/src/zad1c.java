import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;


public class zad1c {
    public static void main(String[] args) {
        Set<String> mySet;
        Set<String> FavFood = new ConcurrentSkipListSet<String>();
        FavFood.add("Kebabs");
        FavFood.add("Chińczyks");
        FavFood.add("Drzemka");
        FavFood.add("Firstborns");

        mySet = Collections.synchronizedSet(FavFood);

        HandleThread(mySet, true);
        HandleThread(mySet, false);
    }

    public static void HandleThread(Set<String> listener, boolean isThatFirstSet) {
        Runnable runnable = () -> {
            synchronized (listener) {
                System.out.println("Uruchomiono: " + Thread.currentThread().getName());
                Iterator<String> iterator = listener.iterator();
                if (isThatFirstSet) {
                    while (iterator.hasNext()) {
                        listener.remove("Firstborns");
                        System.out.println("-> " + iterator.next());
                    }
                    System.out.println("Zakończono: " + Thread.currentThread().getName());
                } else {
                    while (iterator.hasNext()) {
                        listener.add("Spajsy jalapanienino burgers");
                        System.out.println("-> " + iterator.next());
                    }
                    System.out.println("Zakończono: " + Thread.currentThread().getName());
                }
            }
        };
        Thread th;
        if (isThatFirstSet) {
            th = new Thread(runnable, "pierwszy wątek");
        } else {
            th = new Thread(runnable, "drugi wątek");
        }
        th.start();
    }
}