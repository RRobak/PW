package lab15.zad5;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WriterThread implements Runnable {

	@Override
	public void run() {
		Random rand = new Random();
		while (true) {
			Integer newInt=rand.nextInt(100);
			Data.data.set(newInt);
			System.out.println("Writer "+newInt);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}

	}

}
