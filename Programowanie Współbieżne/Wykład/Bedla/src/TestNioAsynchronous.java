import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.TimeUnit;

public class TestNioAsynchronous {
	public static void main(String[] args) throws Exception {
		final boolean interrupt = true;
		final Selector socketSelector = Selector.open();
		final ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.socket().bind(new InetSocketAddress(54321));
		serverChannel.configureBlocking(false);
		serverChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
		final ServerSocketChannel serverChannel2 = ServerSocketChannel.open();
		serverChannel2.socket().bind(new InetSocketAddress(54322));
		serverChannel2.configureBlocking(false);
		serverChannel2.register(socketSelector, SelectionKey.OP_ACCEPT);

		Thread socketThread = new Thread() {
			@Override
			public void run() {
				try {
					System.err.println("BEFORE    " + System.nanoTime());
					int s = -1;
					if ((s = socketSelector.select()) > 0) {
						System.err.println("AFTER " + System.nanoTime());
						// ...
					}
					System.err.println("END " + s + System.nanoTime());
				} catch (Exception e) {
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
			socketSelector.close();
		}
	}
}