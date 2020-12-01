package lab12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModificateThreadVolatile implements Runnable {
	private String name;
	
	

static ModificateValueVolatile value = new ModificateValueVolatile();


public ModificateThreadVolatile(String name) {
	this.name=name;
}
public void run() {

	for (int it=0;it<10;it++){
		value.increaseValue();
		System.out.println(name+":"+value.getValue());
		value.decreaseValue();
	
	System.out.println(name+":"+value.getValue());
	}
}
public static void main(String[] args) {
	ExecutorService executor=Executors.newCachedThreadPool();
	executor.execute(new ModificateThreadVolatile("Watek 1"));
	executor.execute(new ModificateThreadVolatile("Watek 2"));
}
}
