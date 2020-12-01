public class TheSameThreadEchoServer extends EchoServer {

	public TheSameThreadEchoServer(TheSameThreadExecutor executor) {
		super(executor);
	}

	public static void main(String args[]) {
		TheSameThreadEchoServer server = new TheSameThreadEchoServer(new TheSameThreadExecutor());
		server.run();
	}

}

class TheSameThreadExecutor implements SimpleExecutorService {

	@Override
	public void execute(Runnable task) {
		task.run();
	}

	@Override
	public void shutdown() {
	}
	
}