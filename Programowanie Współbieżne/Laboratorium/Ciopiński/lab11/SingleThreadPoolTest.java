package lab11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ClassRunnable implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Witaj tu Mateusz");
		
		
	}
	
	
}

public class SingleThreadPoolTest {
	public static void main(String[] args) {
		ExecutorService executor=Executors.newSingleThreadExecutor();
		executor.execute(new ClassRunnable());
		executor.execute(new ClassRunnable());
		executor.execute(new ClassRunnable());
		executor.execute(new ClassRunnable());
		executor.execute(new ClassRunnable());
		executor.shutdown();
	}
}
