package lab12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModificateThreadSynBlock implements Runnable {

static ModificateValueSynBlock value = new ModificateValueSynBlock();
private String name;
public ModificateThreadSynBlock(String name) {
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
	executor.execute(new ModificateThreadSynBlock("Watek 1"));
	executor.execute(new ModificateThreadSynBlock("Watek 2"));
}
}
