package lab14.zad1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Workers implements Runnable{
	final private Manager manager;
	final private int id;
	public Workers(Manager manager,int id) {
		this.manager=manager;
		this.id=id;
	}
	@Override
	public void run() {
		Random rand=new Random();
		
		try {
			TimeUnit.SECONDS.sleep(rand.nextInt(10));
			while(manager.takeVoice()==false);
			TimeUnit.SECONDS.sleep(rand.nextInt(5));
			manager.returnProposition(this, "Propozycja od pracownika: "+id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}

}

