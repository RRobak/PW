package lab15.zad4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PossionPillTest {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1024);

		try {
			queue.put(1);

			queue.put(2);
			queue.put(3);
			queue.put(4);
			Thread thread = new Thread(new PossionPillThread(queue));
			thread.start();
			queue.put(1);
			queue.put(1);
			queue.put(0);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
