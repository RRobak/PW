import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Z1B {
  public static void main(String[] args) {
    CyclicBarrier cb = new CyclicBarrier(3, new koniec());
    thread th1 = new thread(cb, 1);
    thread th2 = new thread(cb, 2);
    thread th3 = new thread(cb, 3);
    th1.start();
    th2.start();
    th3.start();
    System.out.println("Zakonczono ");
  }
}

class thread extends Thread {
  private CyclicBarrier listener;
  private int thNumber;

  public thread(CyclicBarrier listener, int thInfo) {
    this.listener = listener;
    this.thNumber = thInfo;
  }

  @Override
  public void run() {
    System.out.println("Watek : " + thNumber);
    try {
      listener.await();
    } catch (InterruptedException e) {
      System.out.println(e);
    } catch (BrokenBarrierException e) {
      System.out.println(e);
    }
  }
}

class koniec implements Runnable {

  @Override
  public void run() {
    System.out.println("Wszystkie watki zakonczono");
  }
}
