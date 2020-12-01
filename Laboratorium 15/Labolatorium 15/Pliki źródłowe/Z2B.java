import java.util.ArrayList;

class Z2B {
    ArrayList<Drukarki> printers = new ArrayList<Drukarki>();

    void add(Drukarki s) {
        printers.add(s);
    }

    synchronized public void end(String message) {
        for (Drukarki s : printers) {
            s.after(message);
        }
    }

    static class Drukarki {
        private String name;

        public Drukarki(String name) {
            this.name = name;
        }

        public void after(String wiadomosc) {
            System.out.println(name + " drukuje na " + wiadomosc);
        }
    }

    public static class Drukarka {
        public static void main(String args[]) {
            Z2B d = new Z2B();
            Drukarki a = new Drukarki("Użytkownik a");
            d.add(a);
            d.end("Drukarka A");
            Drukarki b = new Drukarki("Użytkownik b");
            d.add(b);
            d.end("Drukarka A");
            Drukarki c = new Drukarki("Użytkownik c");
            d.add(c);
            d.end("Drukarka A");
        }
    }
}
