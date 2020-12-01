package lab15.zad5;

import java.util.concurrent.TimeUnit;

public class ReaderThread implements Runnable{

	@Override
	public void run() {
		while (true){
			System.out.println("Reader "+Data.data.getAndIncrement());
			try {
				TimeUnit.MILLISECONDS.sleep(400);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}

}
