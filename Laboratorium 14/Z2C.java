import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Z2C {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static String message = "wiadomosc";

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ThreadA());
        thread1.setName("Pierwszy watek");
        Thread thread2 = new Thread(new ThreadB());
        thread2.setName("Drugi watek");
        Thread thread3 = new Thread(new DataHandler());
        thread3.setName("Data reader");
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
    }

    static class DataHandler implements Runnable {
        public void run() {
            if (lock.isWriteLocked()) {
                System.out.println("->is locked");
            }
            lock.readLock().lock();
            try {
                Long duration = (long) (Math.random() * 10000);
                System.out
                        .println(Thread.currentThread().getName() + "  Time Taken " + (duration / 1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + ": " + message);
                lock.readLock().unlock();
            }
        }
    }

    static class ThreadA implements Runnable {
        public void run() {
            lock.writeLock().lock();
            try {
                Long duration = (long) (Math.random() * 10000);
                System.out.println(
                        Thread.currentThread().getName() + " zablokowany przez " + (duration / 1000) + " sekund");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                message = message.concat("A");
                lock.writeLock().unlock();
            }
        }
    }

    static class ThreadB implements Runnable {
        public void run() {
            lock.writeLock().lock();
            try {
                Long duration = (long) (Math.random() * 10000);
                System.out.println(
                        Thread.currentThread().getName() + " zablokowany przez " + (duration / 1000) + " sekund");
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                message = message.concat("B");
                lock.writeLock().unlock();
            }
        }
    }
}