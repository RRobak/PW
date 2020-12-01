import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.*;

public class Relay {
	final private static int RUNNER_NUMBER = 4;
	final private static int CHANGES_NUMBER = RUNNER_NUMBER - 1;
	final private static Baton baton = new Baton();
	final private static HashSet<Runner> runners = new HashSet<Runner>();
	final private static ArrayList<SynchronousQueue<Baton>> changes = new ArrayList<SynchronousQueue<Baton>>();

	public static void main(String[] args) {
		for (int i = 0; i < CHANGES_NUMBER; i++)
			changes.add(new SynchronousQueue<Baton>(true));
		for (int i = 0; i < RUNNER_NUMBER; i++)
			switch (i) {
			case 0:
				runners.add(new Runner(i, baton, null, changes.get(0)));
				break;
			case RUNNER_NUMBER - 1:
				runners.add(new Runner(i, null, changes.get(i - 1), null));
				break;
			default:
				runners.add(new Runner(i, null, changes.get(i - 1), changes.get(i)));
			}
		for (Runner r : runners)
			r.start();
	}
}

class Baton {
}

class Runner extends Thread {
	final private Random r;
	private final String toString;
	final private SynchronousQueue<Baton> changeBefore;
	final private SynchronousQueue<Baton> changeAfter;
	private Baton baton;

	public Runner(int runnerNumber, Baton baton, SynchronousQueue<Baton> changeBefore, SynchronousQueue<Baton> changeAfter) {
		r = new Random(System.nanoTime() + hashCode());
		String changeBeforeToString = changeBefore != null ? changeBefore.getClass().getSimpleName() + "@" + Integer.toHexString(changeBefore.hashCode()) : "X";
		String changeAfterToString = changeAfter != null ? changeAfter.getClass().getSimpleName() + "@" + Integer.toHexString(changeAfter.hashCode()) : "X";
		toString = changeBeforeToString + " >-> " + runnerNumber + " >-> " + changeAfterToString;
		this.baton = baton;
		this.changeBefore = changeBefore;
		this.changeAfter = changeAfter;
	}

	@Override
	public String toString() {
		return toString;
	}

	@Override
	public void run() {
		try {
			if (baton == null)
				baton = changeBefore.take();
			runLap();
			if (changeAfter != null)
				changeAfter.put(baton);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void runLap() throws InterruptedException {
		System.out.println(this + " starts the lap ");
		TimeUnit.SECONDS.sleep(r.nextInt(10));
		System.out.println(this + " ends the lap ");
	}
}