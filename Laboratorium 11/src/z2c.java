class Wartosc {
    synchronized public void Pobierz() {
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class Zmienwartosc extends Thread {
    Wartosc val;
    Zmienwartosc(Wartosc line) {
        this.val = line;
    }
    @Override
    public void run() {
        val.Pobierz();
    }
}

public class z2c {
    public static void main(String[] args) {
        Wartosc obj = new Wartosc();
        Zmienwartosc train1 = new Zmienwartosc(obj);
        Zmienwartosc train2 = new Zmienwartosc(obj);
        train1.start();
        train2.start();
    }
}