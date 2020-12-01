import java.util.concurrent.Phaser;

public class Z1C {

  public static void main(String[] args) {
    Phaser phaser = new Phaser();
    int currentPhase;

    System.out.println("Phaser: ");
    Phase p1 = new Phase(phaser, "Pierwszy");
    Phase p2 = new Phase(phaser, "Drugi");
    Phase p3 = new Phase(phaser, "Trzeci");
    p1.start();
    try {
      Thread.sleep(100);
    } catch (Exception e) {
      e.printStackTrace();
    }
    p2.start();
    p3.start();

    currentPhase = phaser.getPhase();
    phaser.arriveAndAwaitAdvance();
    System.out.println("Faza skonczona : " + ++currentPhase);
    System.out.println("Faza pierwsza zakonczona");

    currentPhase = phaser.getPhase();
    phaser.arriveAndAwaitAdvance();
    System.out.println("Faza skonczona : " + ++currentPhase);
    System.out.println("Faza druga zakonczona");

    phaser.arriveAndDeregister();
    if (phaser.isTerminated()) {
      System.out.println("Phaser zakonczony");
    }
  }
}

class Phase extends Thread {
  Phaser phaser;
  String name;

  public Phase(Phaser phaser, String name) {
    this.phaser = phaser;
    this.name = name;

    phaser.register();
  }

  @Override
  public void run() {
    System.out.println("Watek: " + name + "  Faza pierwsza rozpoczeta");
    phaser.arriveAndAwaitAdvance();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      System.out.println(e);
    }

    System.out.println("Watek: " + name + "  Faza druga rozpoczeta");
    phaser.arriveAndAwaitAdvance();
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }
}
