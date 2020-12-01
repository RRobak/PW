class sleeptest {
    void wypisztab(int n) {
        synchronized (this) {
            for (int i = 1; i <= 5; i++) {
                System.out.println(n * i);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
class Watek1 extends Thread {
    sleeptest t;
    Watek1(sleeptest t) {
        this.t = t;
    }
    public void run() {
        t.wypisztab(5);
    }

}

class Watek2 extends Thread {
    sleeptest t;
    Watek2(sleeptest t) {
        this.t = t;
    }
    public void run() {
        t.wypisztab(100);
    }
}

public class z2b {
    public static void main(String args[]) {
        sleeptest obj = new sleeptest();
        Watek1 t1 = new Watek1(obj);
        Watek2 t2 = new Watek2(obj);
        t1.start();
        t2.start();
    }
}