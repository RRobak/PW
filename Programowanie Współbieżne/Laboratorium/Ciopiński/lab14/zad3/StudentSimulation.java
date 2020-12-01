package lab14.zad3;

import java.util.concurrent.Exchanger;

public class StudentSimulation {

	private static Exchanger<Paper> exchanger = new Exchanger<Paper>();
	private static Paper p0 = new Paper(0);
	private static Paper p1 = new Paper(1);

	public static void main(String[] args) {

		Student s0 = new ReadStudent(exchanger, p0);
		s0.start();
		Student s1 = new WriteStudent(exchanger, p1);
		s1.start();

	}
}
