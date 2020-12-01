import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Z1D {

  public static void main(String[] args) throws InterruptedException {
    Exchanger<Integer> exchanger = new Exchanger<>();

    CreateData t1 = new CreateData(exchanger);
    ReadData t2 = new ReadData(exchanger);

    t1.start();
    t2.start();
    Thread.sleep(1000);
    t1.interrupt();
    t2.interrupt();
  }
}

class CreateData extends Thread {
  Exchanger<Integer> ex;
  int x;
  int initialValue;

  public CreateData(Exchanger<Integer> ex) {
    this.ex = ex;
    initialValue = 40;
    x = initialValue;
  }

  @Override
  public void run() {
    try {
      System.out.println("Wysyłam w exchange point " + initialValue);
      x = ex.exchange(x, 250, TimeUnit.MILLISECONDS);
      x = ex.exchange(x);
    } catch (InterruptedException e) {
      System.out.println(e + " przerwany wątek");
    } catch (TimeoutException t) {
      System.out.println("Timeout exception!");
    }
  }
}

class ReadData extends Thread {
  Exchanger<Integer> ex;
  int y;

  public ReadData(Exchanger<Integer> ex) {
    this.ex = ex;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(50);
      y = ex.exchange(y);
      System.out.println("Handler dostał " + y);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }
}
