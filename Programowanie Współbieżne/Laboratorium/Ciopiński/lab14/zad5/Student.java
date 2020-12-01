package lab14.zad5;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Student extends Thread{
	final private Random r;
	@Override
	public String toString() {
		return "Student [number=" + number + "]";
	}

	private final int number;
	final private SynchronousQueue<Crib> changeBefore;
	final private SynchronousQueue<Crib> changeAfter;
	private Crib crib;

	public Student(int number, Crib crib, SynchronousQueue<Crib> changeBefore,
			SynchronousQueue<Crib> changeAfter) {

		r = new Random();
		this.number = number;
		this.crib = crib;
		this.changeBefore = changeBefore;
		this.changeAfter = changeAfter;
	}

	@Override
	public void run() {
		try {
			if (crib == null)
				crib = changeBefore.take();
			takeCrib();
			if (changeAfter != null)
				changeAfter.put(crib);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void takeCrib() throws InterruptedException {
		System.out.println(this + " zaczyna sciagac ");
		TimeUnit.SECONDS.sleep(r.nextInt(10));
		System.out.println(this + " konczy sciagac ");
		}

}
