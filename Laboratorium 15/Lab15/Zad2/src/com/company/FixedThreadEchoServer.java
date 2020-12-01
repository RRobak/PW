import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadEchoServer extends EchoServer {

	public FixedThreadEchoServer(ExecutorService executor) {
		super(executor);
	}

	private static final int NUMBER_OF_THEADS = 3;

	public static void main(String[] args) {
		FixedThreadEchoServer server = new FixedThreadEchoServer(Executors.newFixedThreadPool(NUMBER_OF_THEADS));
		server.run();
	}

}
