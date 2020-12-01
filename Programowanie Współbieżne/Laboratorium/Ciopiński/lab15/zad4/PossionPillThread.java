package lab15.zad4;

import java.util.concurrent.BlockingQueue;

public class PossionPillThread implements Runnable {

	 BlockingQueue<Integer> queue;

	public PossionPillThread(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			Integer number;
			try {
				number = queue.take();
				System.out.println(number);
				if (number == 0) {
					System.out.println("Udlawilem sie pigulka");
					return;
				}
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			

		}

	}

}
