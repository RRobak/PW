public class Z1A {
    public static void main(String[] args) {
        Server server = new Server();
        server.setRunning(true);
        System.out.println("Server działa");
        Process process = new Process(server);
        process.start();
    }
}

class Server {
    private volatile boolean isRunning = true;
    public boolean isRunning() {
        return this.isRunning;
    }
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
        System.out.println("Ustawiono:" + isRunning);
    }
}

class Process extends Thread {

    private final Server server;
    public Process(Server server) {
        this.server = server;
    }
    @Override
    public void run() {
        int i = 0;
        while (server.isRunning()) {
            try {
                Thread.sleep(100);
                System.out.println(i);
                ++i;
                if (i > 10) {
                    server.setRunning(false);
                    System.out.println("Zakonczenie watku");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}