package lab14.zad4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

final public class Toilet {
	

	final private Semaphore places;
	final private Collection<Cabin> cabines = new HashSet<Cabin>();

	public Toilet(ArrayList<Cabin> collection) {
		if (collection == null)
			throw new NullPointerException();
		for (Cabin e : collection) {
			if (e == null)
				throw new NullPointerException();
			cabines.add(new Cabin());
		}
		places = new Semaphore(cabines.size());
	}

	int getAvailable() {
		return places.availablePermits();
		
	}

	public void go() throws InterruptedException {
		places.acquire();
		System.out.println("Wchodze do lazienki");
	}

	public void out() {

		places.release();
		System.out.println("Wychodze z lazienki");
	}

}
