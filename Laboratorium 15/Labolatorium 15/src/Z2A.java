import java.util.ArrayList;

class Z2A {
    ArrayList<User> Pasazer = new ArrayList<User>();
    void zapisz(User s) {
        Pasazer.add(s);
    }
    synchronized public void leave(String wiadomosc) {
                    for (User s : Pasazer) {
                        s.add(wiadomosc);
                    }
                }

    static class User {
        private String name;
        public User(String name) {
            this.name = name;
        }
        public void add(String message) {
            System.out.println(name + " wszedł " + message);
        }
    }
    public static void main(String args[]) {
        Z2A x = new Z2A();
        User a = new User("A");
        x.zapisz(a);;
        x.leave("Winda 1");
        User b = new User("B");
        x.zapisz(b);
        x.leave("Winda 2");
        User c = new User("C");
        x.zapisz(c);
        x.leave("Winda 3");
        User d = new User("D");
        x.zapisz(d);
        x.leave("Winda 1");
        User e = new User("E");
        x.zapisz(e);
        x.leave("Winda 2");
        User f = new User("F");
        x.zapisz(f);
        x.leave("Winda 3");
    }
}