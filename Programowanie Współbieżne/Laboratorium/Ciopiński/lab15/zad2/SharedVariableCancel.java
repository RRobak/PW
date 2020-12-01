package lab15.zad2;

import java.util.concurrent.TimeUnit;



public class SharedVariableCancel extends Thread{
	
	
	@Override
	public void run() {
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("Zadanie anulowane");
			
		}
		System.out.println("Zadanie zakonczone");
		
	}

}
