package lab12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModificateThreadSynMethod implements Runnable {

static ModificateValueSynMethod value = new ModificateValueSynMethod();

private String name;

public ModificateThreadSynMethod(String name) {
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
	executor.execute(new ModificateThreadSynMethod("Watek 1"));
	executor.execute(new ModificateThreadSynMethod("Watek 2"));
}
}
