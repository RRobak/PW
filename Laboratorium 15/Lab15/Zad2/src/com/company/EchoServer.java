import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;

public abstract class EchoServer implements Runnable {

	private ServerSocket server;
	private ExecutorService executor;

	public EchoServer(ExecutorService executor) {
		if (executor == null)
			throw new NullPointerException();
		this.executor = executor;
		try {
			server = new ServerSocket(54321);
			server.setSoTimeout(10_000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Server startup");

		try {
			for (;;) {
				Socket socket = server.accept();
				process(socket);
			}

		} catch (SocketTimeoutException e) {
			System.out.println("Server shutdown");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			shutdown();
		}
	}

	protected void process(Socket socket) {
		EchoTask task = new EchoTask(socket);
		executor.execute(task);
	}

	protected void shutdown() {
		executor.shutdown();
	}
}
