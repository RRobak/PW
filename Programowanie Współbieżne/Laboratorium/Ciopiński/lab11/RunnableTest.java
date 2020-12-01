package lab11;

class RunnableClass implements Runnable {

	@Override
	public void run() {
		System.out.println("Witaj tu Mateusz");
		
	}
	
	
}


public class RunnableTest {

	
	public static void main(String[] args) {
		Thread watek=new Thread(new RunnableClass());
		watek.start();
	}
	
}
