package lab14.zad5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.SynchronousQueue;

public class Exam {
	final private static int STUDENTS_NUMBER = 5;
	final private static int CHANGES_NUMBER = STUDENTS_NUMBER - 1;
	final private static Crib crib = new Crib();
	final private static HashSet<Student> students =
			new HashSet<Student>();
	final private static ArrayList<SynchronousQueue<Crib>> changes =
			new ArrayList<SynchronousQueue<Crib>>();
	
	public static void main(String[] args) {
		for (int i = 0; i < CHANGES_NUMBER; i++)
			changes.add(new SynchronousQueue<Crib>(true));
			for (int i = 0; i < STUDENTS_NUMBER; i++)
			switch (i) {
			case 0:
				students.add(new Student(i, crib, null, changes.get(0)));
			break;
			case STUDENTS_NUMBER - 1:
				students.add(new Student(i, null, changes.get(i - 1),null));
			break;
			default:
				students.add(new Student(i, null,
			changes.get(i - 1), changes.get(i)));
			}
			for (Student r : students) r.start();
			}
	
}
