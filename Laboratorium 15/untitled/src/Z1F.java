
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Z1F {
  static final int listeners = 5;

  public static void main(String[] args) {
    BlockingQueue<Integer> syncQueue = new SynchronousQueue<>();
    reader read = new reader(syncQueue);
    read.start();
    writer[] wqueue = new writer[listeners];
    for (int i = 0; i < listeners; i++) {
      wqueue[i] = new writer(syncQueue);
      wqueue[i].start();
    }
  }
}

class reader extends Thread {
  private BlockingQueue<Integer> q;

  public reader(BlockingQueue<Integer> q) {
    this.q = q;
  }

  private int GetRandom() {
    Random gen = new Random();
    int number = gen.nextInt(100);
    try {
      Thread.sleep(gen.nextInt(100));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Dodano " + number);
    return number;
  }

  public void run() {
    while (true) {
      try {
        q.put(GetRandom());
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }
}

class writer extends Thread {
  private BlockingQueue<Integer> q;

  public writer(BlockingQueue<Integer> q) {
    this.q = q;
  }

  private void write(int number) {
    String message = "Wiadomosc: " + getName();
    message += " Wiadomosc: " + number;
    System.out.println(message);
  }

  public void run() {
    while (true) {
      try {
        int number = q.take();
        write(number);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
    }
  }
}
