class MyRunnable implements Runnable {
	public void run() {
		// ...
	}
}

public class TestRunnable {

	public static void main(String[] args) {
		Thread t = new Thread(new MyRunnable());
		t.start();
	}
}