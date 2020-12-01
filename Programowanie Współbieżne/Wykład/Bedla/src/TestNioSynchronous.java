import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.TimeUnit;

public class TestNioSynchronous {
	public static void main(String[] args) throws Exception {
		final boolean interrupt = true;
		final ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.socket().bind(new InetSocketAddress(54321));
		Thread socketThread = new Thread() {
			@Override
			public void run() {
				try {
					System.err.println("BEFORE    " + System.nanoTime());
					serverChannel.accept();
					System.err.println("AFTER     " + System.nanoTime());
					// ...
				} catch (IOException e) {
					System.err.println("EXCEPTION " + System.nanoTime());
					e.printStackTrace();
				}
			}
		};
		socketThread.start();
		if (interrupt) {
			TimeUnit.SECONDS.sleep(5);
			System.err.println("INTERRUPT " + System.nanoTime());
			socketThread.interrupt();
		} else {
			TimeUnit.SECONDS.sleep(5);
			System.err.println("CLOSE     " + System.nanoTime());
			serverChannel.close();
		}
	}
}