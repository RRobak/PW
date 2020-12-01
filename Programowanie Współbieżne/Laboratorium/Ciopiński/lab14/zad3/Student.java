package lab14.zad3;

import java.util.Random;
import java.util.concurrent.Exchanger;

public abstract class Student extends Thread {

	protected final Random r;
	private final Exchanger<Paper> exchanger;
	private Paper paper;

	public Student(Exchanger<Paper> exchanger, Paper initialPaper) {
		r = new Random(this.hashCode());
		this.exchanger = exchanger;
		this.paper = initialPaper;

	}

	abstract void work(Paper paper) throws InterruptedException;

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println(this);
				work(paper);
				System.out.println(this);
				paper = exchanger.exchange(paper);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Student ["+"paper="
				+ paper + "]";
	}
}
