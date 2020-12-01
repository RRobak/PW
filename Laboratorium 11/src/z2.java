
public class z2 {

    private static volatile int pom = 0;
    public static void main(String[] args) {
        new Sprawdz().start();
        new Zmiana().start();
    }
    static class Sprawdz extends Thread {
        @Override
        public void run() {
            int pomzapis = pom;
            while (pomzapis < 5) {
                if (pomzapis != pom) {
                    System.out.println("Zapis: " + pom);
                    pomzapis = pom;
                }
            }
        }
    }

    static class Zmiana extends Thread {
        @Override
        public void run() {
            int pomodczyt = pom;
            while (pom < 5) {
                System.out.println("Odczyt: " + pom);
                pom = ++pomodczyt;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}