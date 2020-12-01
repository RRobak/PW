import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

final class Rental<V> {

	private static class Entry<E> {
		boolean used;
		final E object;

		Entry(E object) {
			this.object = object;
		}
	}

	final private Semaphore permits;
	final private Collection<Entry<V>> entries = new HashSet<Entry<V>>();

	Rental(Collection<V> collection) {
		if (collection == null)
			throw new NullPointerException();
		for (V e : collection) {
			if (e == null)
				throw new NullPointerException();
			entries.add(new Entry<V>(e));
		}
		permits = new Semaphore(entries.size());
	}

	int getAvailable() {
		return permits.availablePermits();
	}

	public V rent() throws InterruptedException {
		permits.acquire();
		return getNext();
	}

	private synchronized V getNext() {
		for (Entry<V> e : entries)
			if (!e.used) {
				e.used = true;
				return e.object;
			}
		return null;
	}

	public void giveBack(V object) {
		if (tryReturn(object))
			permits.release();
	}

	private synchronized boolean tryReturn(V c) {
		for (Entry<V> e : entries)
			if (e.object == c) {
				if (!e.used)
					return false;
				e.used = false;
				return true;
			}
		return false;
	}
}

class Car {
}

public class TestCarRental {
	public static void main(String[] args) throws InterruptedException {
		ArrayList<Car> cars = new ArrayList<Car>();
		for (int i = 0; i < 4; i++)
			cars.add(new Car());

		Rental<Car> carRental = new Rental<Car>(cars);
		System.out.println("Available cars: " + carRental.getAvailable());

		Car c = carRental.rent();
		System.out.println("Rented car     : " + c);
		System.out.println("Available cars: " + carRental.getAvailable());

		Car c2 = carRental.rent();
		System.out.println("Rented car     : " + c2);
		System.out.println("Available cars: " + carRental.getAvailable());

		carRental.giveBack(c2);
		System.out.println("Available cars: " + carRental.getAvailable());

		carRental.giveBack(c2);
		System.out.println("Available cars: " + carRental.getAvailable());
		Car tmp = new Car();
		carRental.giveBack(tmp);
		System.out.println("Returned car   : " + tmp);
		System.out.println("Available cars: " + carRental.getAvailable());
	}
}