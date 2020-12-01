import java.util.HashSet;
import java.util.Set;

public class OtherThreadEchoServer extends EchoServer {

	public OtherThreadEchoServer(OtherThreadExecutor executor) {
		super(executor);
	}

	public static void main(String args[]) {
		OtherThreadEchoServer server = new OtherThreadEchoServer(new OtherThreadExecutor());
		server.run();
	}

}

class OtherThreadExecutor implements SimpleExecutorService {
	
	private Set<Thread> threads = new HashSet<>();

	@Override
	public void execute(Runnable task) {
		Thread newThread = new Thread(task);
		threads.add(newThread);
		newThread.start();
	}
	
	@Override
	public void shutdown() {
		for(Thread t : threads) {
			if(t.isAlive())
				t.interrupt();
		}
	}
	
}