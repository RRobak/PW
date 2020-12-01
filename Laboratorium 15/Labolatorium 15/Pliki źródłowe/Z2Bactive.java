
import java.util.concurrent.*;
import java.util.*;

public class Z2Bactive {
    private ExecutorService ex = Executors.newSingleThreadExecutor();

    public Future<Integer> Print(final int Number) {
        return ex.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                System.out.print("Drukuje dokument " + Number + "\n");
                TimeUnit.MILLISECONDS.sleep(100);
                return Number;
            }
        });
    }

    public Future<Integer> Abort(final int Number) {
        return ex.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                System.out.print("Wydrukowalem " + Number + "\n");
                TimeUnit.MILLISECONDS.sleep(100);
                return Number;
            }
        });
    }

    public void shutdown() {
        ex.shutdown();
    }

    public static void main(String[] args) {
        Z2Bactive d1 = new Z2Bactive();
        List<Future<?>> results = new CopyOnWriteArrayList<Future<?>>();
        for (int i = 0; i < 5; i++) {
            results.add(d1.Print(i));
            results.add(d1.Abort(i));
        }
        d1.shutdown();
    }
}