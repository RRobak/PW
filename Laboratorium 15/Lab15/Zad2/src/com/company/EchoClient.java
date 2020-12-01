import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EchoClient {

	private static final int NUMBER_OF_CLIENTS = 16;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		Instant start = Instant.now();
		for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
			executor.execute(new Client());
		}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		Instant finish = Instant.now();
		long timeMiliis = Duration.between(start, finish).toMillis();
		System.out.println("Time (ms): " + timeMiliis);

	}
}

class Client implements Runnable {
	private Random r = new Random();

	@Override
	public void run() {
		try (Socket socket = new Socket("localhost", 54321);
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream();) {

			byte bWrite = (byte) r.nextInt(Byte.MAX_VALUE);

			os.write(bWrite);

			byte bRead = (byte) is.read();
			System.out.println("Write = " + bWrite + " Read = " + bRead);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
