
import java.util.concurrent.*;
import java.util.*;

public class Z2Aactive {
    private ExecutorService ex = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.print("Przerwanie zadania w sleep()");
        }
    }

    public Future<Integer> ADD(final int lp) {
        return ex.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                System.out.print("Wsiada pasazer " + lp + "\n");
                TimeUnit.MILLISECONDS.sleep(100);
                return lp;
            }
        });
    }

    public Future<Integer> LEAVE(final int lp) {
        return ex.submit(new Callable<Integer>() {
            public Integer call() throws InterruptedException {
                System.out.print("Wysiada pasazer " + lp + "\n");
                TimeUnit.MILLISECONDS.sleep(100);
                return lp;
            }
        });
    }

    public void shutdown() {
        ex.shutdown();
    }

    public static void main(String[] args) {
        Z2Aactive d1 = new Z2Aactive();
        List<Future<?>> results = new CopyOnWriteArrayList<Future<?>>();
        for (int i = 0; i < 5; i++)
            results.add(d1.ADD(i));
        for (int i = 0; i < 5; i++)
            results.add(d1.LEAVE(i));
        d1.shutdown();
    }
}
