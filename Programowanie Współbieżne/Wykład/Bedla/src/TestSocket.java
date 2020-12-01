import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.TimeUnit;

public class TestSocket {
	public static void main(String[] args) throws Exception {
		final ServerSocket socket = new ServerSocket(12345);
		Thread socketThread = new Thread() {
			@Override
			public void run() {
				try {
					System.err.println("BEFORE    " + System.nanoTime());
					socket.accept();
					System.err.println("AFTER     " + System.nanoTime());
					// ...
				} catch (IOException e) {
					System.err.println("EXCEPTION " + System.nanoTime());
					e.printStackTrace();
				}
			}
		};
		socketThread.start();
		TimeUnit.SECONDS.sleep(5);
		System.err.println("INTERRUPT " + System.nanoTime());
		socketThread.interrupt();
		TimeUnit.SECONDS.sleep(5);
		System.err.println("CLOSE     " + System.nanoTime());
		socket.close();
	}
}