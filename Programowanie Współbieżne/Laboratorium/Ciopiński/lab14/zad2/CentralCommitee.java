package lab14.zad2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CentralCommitee implements Runnable {
	final private static int NUMBER_OF_LOCAL_COMMITEE = 5;
	final private LocalCommitee[] localCommitees = new LocalCommitee[NUMBER_OF_LOCAL_COMMITEE];
	final private CyclicBarrier barrier;
	private Results globalResults;
	private int ture=0;

	public CentralCommitee() {
		barrier = new CyclicBarrier(NUMBER_OF_LOCAL_COMMITEE, this);
		for (int i = 0; i < localCommitees.length; i++) {
			localCommitees[i] = new LocalCommitee(this, i);
			localCommitees[i].start();
		}
	}

	@Override
	public void run() {
		globalResults=new Results();
	
		for (int i = 0; i < localCommitees.length; i++) {
		Results tmp = localCommitees[i].getLocalResults();
		
		globalResults.addNo(tmp.getNo());
		globalResults.addYes(tmp.getYes());
		}
		System.out.println("Tura: "+ture+++" Na tak: "+globalResults.getYes()+" Na nie: "+globalResults.getNo());
		

	}

	public Results getGlobalResults() throws InterruptedException, BrokenBarrierException {
		barrier.await();
		
		return globalResults;
	}
	
	public static void main(String[] args) {
		new CentralCommitee();
		
		
	}

}
