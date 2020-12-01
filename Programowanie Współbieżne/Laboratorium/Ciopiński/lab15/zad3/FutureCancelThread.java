package lab15.zad3;

import java.util.concurrent.TimeUnit;

class FutureCancelThread extends Thread {
	public void run() {
		System.out.println("Witaj,cos CI powiem jak nie zostane zatrzymany");
		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.println("Cos");
		} catch (InterruptedException e) {
			System.out.println("Zostalem zatrzymany wiec Ci nie powiem");
		}
		System.out.println("Zakonczony");
	}
	
}