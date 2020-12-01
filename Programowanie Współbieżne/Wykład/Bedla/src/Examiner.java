import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Student extends Thread {
	final private Examiner ex;

	Student(Examiner ex) {
		this.ex = ex;
	}

	@Override
	public void run() {
		try {
			String question = ex.getQuestion(this);
			String answer = exam(question);
			ex.returnAnswer(this, answer);
		} catch (InterruptedException e) {
			System.err.printf("Student  %2d: the exam was cancelled%n", this.getId());
		}
	}

	private String exam(String question) throws InterruptedException {
		TimeUnit.SECONDS.sleep(new Random(hashCode() + getId()).nextInt(10));
		return "" + this.getName();
	}
}

public class Examiner {
	final private static int STUDENT_NUMER = 4;
	final private static CountDownLatch beginExam = new CountDownLatch(1);
	final private static CountDownLatch finishExam = new CountDownLatch(STUDENT_NUMER);
	final private Map<Long, String> answers = new ConcurrentHashMap<Long, String>();

	String getQuestion(Student s) throws InterruptedException {
		Examiner.beginExam.await();
		System.err.printf("Student %2d  began    the exam at %12d%n", s.getId(), System.nanoTime());
		return "What is your name?";
	}

	void returnAnswer(Student s, String answer) {
		System.err.printf("Student %2d  finished the exam at %12d%n", s.getId(), System.nanoTime());
		this.answers.put(s.getId(), answer);
		Examiner.finishExam.countDown();
	}

	private void beginExam() {
		Examiner.beginExam.countDown();
		System.err.printf("Examiner    began    the exam at %12d%n", System.nanoTime());
	}

	private void finishExam() throws InterruptedException {
		Examiner.finishExam.await();
		System.err.printf("Examiner    finished the exam at %12d%n", System.nanoTime());
		System.err.println("Answers: " + answers);
	}

	public static void main(String[] args) {
		try {
			Examiner ex = new Examiner();
			for (int i = 0; i < STUDENT_NUMER; i++)
				new Student(ex).start();
			ex.beginExam();
			ex.finishExam();
		} catch (InterruptedException e) {
			System.err.printf("Examiner: the exam was cancelled%n");
		}
	}
}