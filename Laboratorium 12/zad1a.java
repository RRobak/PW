import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class zad1a {
    public static void main(String[] args) {
        Set<String> mySet;
        HashSet<String> FavFood = new HashSet<>();
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
                while (iterator.hasNext()) {
                    String currValue = iterator.next();
                    System.out.println(Thread.currentThread().getName() + " -> " + currValue);
                }
                System.out.println("Zakończono: " + Thread.currentThread().getName());
            }
        };
        Thread th;
        if (isThatFirstSet) {
            th = new Thread(runnable, "First Thread");
        } else {
            th = new Thread(runnable, "Second Thread");
        }
        th.start();
    }
}