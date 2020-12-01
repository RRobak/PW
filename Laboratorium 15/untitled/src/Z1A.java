import java.util.concurrent.CountDownLatch;

public class Z1A {

  public static void main(String args[]) throws InterruptedException {
    CountDownLatch cdl = new CountDownLatch(3);
    MyThread th1 = new MyThread(cdl, 1);
    MyThread th2 = new MyThread(cdl, 2);
    MyThread th3 = new MyThread(cdl, 3);
    th1.start();
    th2.start();
    th3.start();
    cdl.await();
    System.out.println("All threads finished");
  }
}

class MyThread extends Thread {
  protected CountDownLatch listener;
  private int thNumber;

  public MyThread(CountDownLatch listener, int thInfo) {
    this.listener = listener;
    this.thNumber = thInfo;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(1000 * thNumber);
      listener.countDown();
      System.out.println(
        "Inside -> " + Thread.currentThread().getName() + ", Zakonczono"
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
