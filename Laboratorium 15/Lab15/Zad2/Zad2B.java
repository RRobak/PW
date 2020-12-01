import java.util.concurrent.*;
import java.util.*;

public class Zad2B {

    private ExecutorService ex = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);

    // Losowe opóźnienie dające feket długicj obliczeń
    private void pause(int factor) {

        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        }catch (InterruptedException e) {
            System.out.println("Przerwanie zadania w sleep()");
        }
    }

    public Future<Integer> printDocument(final int x) {

        return ex.submit(new Callable<Integer>() {
            public Integer call() {
                System.out.println( "Zaczynam drukować dokument nr. " + x );
                pause(500);
                System.out.println( "Wydrukowałam  dokument nr. " + x );
                return x;
            }
        });
    }

    public void shutdown() {
        ex.shutdown();
    }

    public static void main(String[] args) {

        Zad2B d1 = new Zad2B();
        // Blokada wyjątku ConcurrentModificationExeption:
        List<Future<?>> results = new CopyOnWriteArrayList<Future<?>>();

        for (int i = 1; i <= 10; i++)
            results.add(d1.printDocument(i));

        System.out.println("Wykonano wszystkie wywołania asynchroniczne");

        while (results.size() > 0) {

            for (Future<?> f : results) {

                if (f.isDone()) {
                    try {
                        //System.out.println(f.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    results.remove(f);
                }
            }
        }

        d1.shutdown();
    }
}

