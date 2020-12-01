import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadEchoServer extends EchoServer {

	public SingleThreadEchoServer(ExecutorService executor) {
		super(executor);
	}

	public static void main(String args[]) {
		SingleThreadEchoServer server = new SingleThreadEchoServer(Executors.newSingleThreadExecutor());
		server.run();
	}

}
