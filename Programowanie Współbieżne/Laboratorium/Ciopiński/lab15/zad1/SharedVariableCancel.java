package lab15.zad1;

public class SharedVariableCancel implements Runnable{
	
	
	@Override
	public void run() {
		int i=0;
		while (!SharedVariable.isCancel()){
			System.out.println(i++);
		}
		System.out.println("Zadanie anulowane");
		
	}

}
