package lab15.zad1;

import java.util.concurrent.TimeUnit;

public class CancelThread {

	public static void main(String[] args) throws InterruptedException {
		
		Thread thread=new Thread(new SharedVariableCancel());
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		SharedVariable.cancel();
		
	}
	
}
