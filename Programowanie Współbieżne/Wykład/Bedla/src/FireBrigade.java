import java.util.Random;
import java.util.concurrent.*;

class Bucket {
	volatile boolean empty;
	private int number;

	Bucket(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Bucket" + number + ": " + (empty ? "empty" : "filled");
	}
}

abstract class FireFighter extends Thread {
	protected final Random r;
	private final Exchanger<Bucket> exchanger;
	private Bucket b;

	FireFighter(Exchanger<Bucket> exchanger, Bucket initialBucket) {
		r = new Random(this.hashCode());
		this.exchanger = exchanger;
		this.b = initialBucket;
	}

	abstract void work(Bucket b) throws InterruptedException;

	@Override
	public void run() {
		try {
			while (true) {
				System.err.printf("%-17s start the work (%12d)%n", this, System.nanoTime());
				work(b);
				System.err.printf("%-17s end   the work (%12d)%n", this, System.nanoTime());
				b = exchanger.exchange(b);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}

class FireFighterFill extends FireFighter {

	FireFighterFill(Exchanger<Bucket> exchanger, Bucket initialBucket) {
		super(exchanger, initialBucket);
	}

	@Override
	void work(Bucket b) throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt(10));
		b.empty = false;
		System.err.println(b);
	}
}

class FireFighterEmpty extends FireFighter {

	FireFighterEmpty(Exchanger<Bucket> exchanger, Bucket initialBucket) {
		super(exchanger, initialBucket);
	}

	@Override
	void work(Bucket b) throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt(10));
		b.empty = true;
		System.err.println(b);
	}
}

public class FireBrigade {

	private static Exchanger<Bucket> exchanger = new Exchanger<Bucket>();
	private static Bucket b0 = new Bucket(0);
	private static Bucket b1 = new Bucket(1);

	public static void main(String[] args) {
		FireFighter f0 = new FireFighterFill(exchanger, b0);
		f0.start();
		FireFighter f1 = new FireFighterEmpty(exchanger, b1);
		f1.start();
	}
}