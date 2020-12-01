public class z2d {
    public static void main(String[] args) {
        Watek3 Watek = new Watek3();
        Watek.start();
        synchronized (Watek) {
            try {
                System.out.println("czekam na wątek");
                Watek.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Suma to: " + Watek.sum);
        }
    }
}

class Watek3 extends Thread {
    int sum;
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            notify();
        }
    }
}