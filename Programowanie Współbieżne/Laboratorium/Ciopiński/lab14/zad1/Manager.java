package lab14.zad1;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

public class Manager {
	final private static int WORKERS_NUMBER = 5;
	final private static CountDownLatch beginMeeting = new CountDownLatch(1);
	final private static CountDownLatch finishMeeting = new CountDownLatch(
			WORKERS_NUMBER);
	private LinkedList<String> propositions = new LinkedList<String>();
	private boolean voiceFree=true;

	private void beginMeeting() {
		beginMeeting.countDown();
		System.out.println("Manager begin meeting");
	}

	private void finishMeeting() throws InterruptedException {
		finishMeeting.await();
		System.out.println("Manager zakonczyl spotkanie");
		
		System.out.println(propositions);
	}

	public boolean takeVoice() throws InterruptedException {
		if(voiceFree==true){
		beginMeeting.await();
		return true;
		}
		else
			return false;
	}

	public void returnProposition(Workers s, String proposition) {
		finishMeeting.countDown();
		System.out.println("Pracownik zabral glos: "+ proposition);
		propositions.add(proposition);
		voiceFree=true;
	}
	public static void main(String[] args) {
		try {
		Manager manager=new Manager();
		for (int i = 0; i < WORKERS_NUMBER; i++){
			Thread worker=new Thread(new Workers(manager,i));
			worker.start();
		}
		manager.beginMeeting();
		manager.finishMeeting();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
