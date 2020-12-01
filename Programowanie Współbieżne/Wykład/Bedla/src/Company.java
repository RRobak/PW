import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class Department extends Thread {

	private volatile double sales;
	final private Random r;
	final private Company c;
	final private int deptNo;

	Department(Company c, int deptNo) {
		this.c = c;
		this.deptNo = deptNo;
		r = new Random(hashCode() + getId());
	}

	double getDepartmentSales() {
		return sales;
	}

	@Override
	public void run() {
		try {
			while (true) {
				calculateSales();
				System.err.printf("Dept. No. %1d  Sales        : %4.0f (%15d)%n", this.deptNo, sales, System.nanoTime());
				double globalPremiumPart = c.getGlobalPremiumPart();
				System.err.printf("Dept. No. %1d  Total premium: %4.0f (%15d)%n", this.deptNo, (0.1 * sales + globalPremiumPart), System.nanoTime());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	void calculateSales() throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt(10));
		sales = r.nextInt(1000);
	}
}

public class Company implements Runnable {

	final private static int NUMBER_OF_DEPARMENTS = 3;
	final private Department[] departments = new Department[NUMBER_OF_DEPARMENTS];
	final private CyclicBarrier barrier;
	private double globalPremiumPart;
	private int monthNo;

	public static void main(String[] args) {
		new Company();
	}

	Company() {
		barrier = new CyclicBarrier(NUMBER_OF_DEPARMENTS, this);
		for (int i = 0; i < departments.length; i++) {
			departments[i] = new Department(this, i);
			departments[i].start();
		}
	}

	@Override
	public void run() {
		double globalSales = 0;
		for (int i = 0; i < departments.length; i++) {
			double tmp = departments[i].getDepartmentSales();
			System.err.printf("Dept. No. %1d %4.0f%n", i, tmp);
			globalSales += tmp;
		}

		globalPremiumPart = 0.1 * globalSales / NUMBER_OF_DEPARMENTS;
		System.err.printf("Month No. %1d  Global part of the premium: %4.0f%n", monthNo++, globalPremiumPart);
	}

	double getGlobalPremiumPart() throws InterruptedException, BrokenBarrierException {
		barrier.await();
		return globalPremiumPart;
	}

}