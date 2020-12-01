package lab11;

class ThreadClass extends Thread{

	@Override
	public void run() {
		System.out.println("Witaj tu Mateusz");
		
	}
	
	
}


public class ThreadTest {

	
	public static void main(String[] args) {
		Thread watek=new ThreadClass();
		watek.start();
	}
	
}
