class Buffer {
	int value;
}

class ReaderThread extends Thread {
	final private Buffer b;

	ReaderThread(Buffer b) {
		this.b = b;
	}

	public void run() {
		try {
			while (true) {
				synchronized (b) {
					System.err.println(">");
					b.wait();
					System.err.println(">> ReaderThread " + b.value);
					b.notify();
					System.err.println(">>>");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}

class WriterThread extends Thread {

	final private Buffer b;
	private int i = 0;

	WriterThread(Buffer b) {
		this.b = b;
	}

	public void run() {
		try {
			while (true) {
				synchronized (b) {
					int t = ++i;
					b.value = t;
					Thread.sleep(t);
					System.err.println("< WriterThread " + b.value);
					b.notify();
					System.err.println("<<");
					b.wait();
					System.err.println("<<<");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}
}

public class TestObject extends Thread {

	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		WriterThread writer = new WriterThread(buffer);
		ReaderThread reader = new ReaderThread(buffer);
		reader.start();
		writer.start();
	}
}