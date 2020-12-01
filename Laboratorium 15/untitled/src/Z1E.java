import java.util.concurrent.Semaphore;

public class Z1E {

  public static void main(String args[]) throws InterruptedException {
    Semaphore sm = new Semaphore(1);
    SemJob th1 = new SemJob(sm, "Pierwszy");
    SemJob th2 = new SemJob(sm, "Drugi");

    th1.start();
    th2.start();

    th1.join();
    th2.join();

    System.out.println("Koncowa wartosc: " + Shared.iterator);
  }
}

class Shared {
  static int iterator = 0;
}

class SemJob extends Thread {
  Semaphore sm;
  String name;

  public SemJob(Semaphore sm, String threadName) {
    super(threadName);
    this.sm = sm;
    this.name = threadName;
  }

  @Override
  public void run() {
    if (this.getName().equals("Pierwszy")) {
      try {
        System.out.println("Rozpoczecie watku" + name);
        sm.acquire();
        System.out.println(name + " dostep potwierdzony");
        for (int i = 0; i < 5; i++) {
          ++Shared.iterator;
          System.out.println(name + ": " + Shared.iterator);
          Thread.sleep(10);
        }
      } catch (InterruptedException e) {
        System.out.println(e + "przerwany wątek ");
      }
      System.out.println(name + " dostep zakonczony.");
      sm.release();
      System.out.println("Wartosc iteratora: " + Shared.iterator);
    } else {
      try {
        System.out.println("Rozpoczynanie watku" + name);
        sm.acquire();
        System.out.println(name + " dostep potwierdzony");
        for (int i = 0; i < 5; i++) {
          --Shared.iterator;
          System.out.println(name + ": " + Shared.iterator);
          Thread.sleep(10);
        }
      } catch (InterruptedException e) {
        System.out.println(e + "przerwany wątek ");
      }
      System.out.println(name + " dostep zakonczony.");
      sm.release();
    }
  }
}
