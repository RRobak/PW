package lab14.zad2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;

public class LocalCommitee extends Thread {
	private volatile Results results = new Results();
	private CentralCommitee centralCommitee;
	private int id;
	final private Random r;

	public LocalCommitee(CentralCommitee centralCommitee, int id) {
		this.centralCommitee = centralCommitee;
		this.id = id;
		r = new Random();
	}

	@Override
	public void run() {
		try {
			while (true) {
				calculateResults();
				System.out.println("Nr komisji lokalnej: " + id + " Na tak: "
						+ results.getYes() + " Na nie: " + results.getNo());
				

				Results globalResults = centralCommitee.getGlobalResults();
				System.out.println("Komisja nr: " + id
						+ "dostala wyniki! Na tak: " + globalResults.getYes()
						+ " Na nie: " + globalResults.getNo());
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

	private void calculateResults() {

		results.setNo(r.nextInt(20));
		results.setYes(r.nextInt(20));

	}

	public Results getLocalResults() {
		return results;
	}

}
