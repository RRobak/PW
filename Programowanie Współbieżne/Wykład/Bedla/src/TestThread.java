class MyThread extends Thread {
	@Override
	public void run() {
		// ...
	}
}

public class TestThread {

	public static void main(String[] args) {
		Thread t = new MyThread();
		t.start();
	}
}