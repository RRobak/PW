package lab15.zad2;


public class CancelThread {

	public static void main(String[] args){
		Thread thread=new Thread(new SharedVariableCancel());
		thread.start();
		thread.interrupt();
		
		
		
	}
	
	
}
