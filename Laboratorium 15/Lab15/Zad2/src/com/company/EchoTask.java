import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class EchoTask implements Runnable {

	private Socket socket;

	public EchoTask(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			byte b = (byte) is.read();

			TimeUnit.SECONDS.sleep(1);

			OutputStream os = socket.getOutputStream();
			os.write(b);
			os.flush();
			System.out.println("Read = " + b + " Write = " + b);

			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}