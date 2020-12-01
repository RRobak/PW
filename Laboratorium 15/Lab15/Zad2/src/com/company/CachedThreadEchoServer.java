import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadEchoServer extends EchoServer {

	public CachedThreadEchoServer(ExecutorService executor) {
		super(executor);
	}

	public static void main(String args[]) {
		CachedThreadEchoServer server = new CachedThreadEchoServer(Executors.newCachedThreadPool());
		server.run();
	}

}
