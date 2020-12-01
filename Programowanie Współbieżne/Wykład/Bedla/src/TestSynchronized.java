public class TestSynchronized extends Thread {
	final private static int SIZE = 4;

	static MyObject value = new MyObject();

	public void run() {
		int i;
		while (true) {
			i = value.method();
			switch (i) {
			case 0:
			case 1:
				break;
			default:
				System.err.println("ERROR " + i);
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {

		TestSynchronized[] t = new TestSynchronized[SIZE];
		for (int i = 0; i < SIZE; i++) {
			t[i] = new TestSynchronized();
			t[i].start();
		}
	}
}

class MyObject {

	private int i = 0;

	/* synchronized */public int method() {
		/* synchronized (this) */{
			if (i == 0)
			/* synchronized (this) */{
				return ++i;
			}
		}
		/* synchronized (this) */{
			if (i == 1)
			/* synchronized (this) */{
				return --i;
			}
			return i;
		}
	}
}
