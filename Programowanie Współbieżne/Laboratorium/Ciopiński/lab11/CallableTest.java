package lab11;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableClass implements Callable<String>{
	
	String name;

	public CallableClass(String name) {
		this.name=name;
	}
	
	@Override
	public String call() throws Exception {
		return "Witaj " + name;
	}
	
}

public class CallableTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		  ExecutorService service =  Executors.newSingleThreadExecutor();
		   CallableClass callableClass = new CallableClass("Mateusz");
		   Future<String> future = service.submit(callableClass);
		   String result = future.get();
		   System.out.println(result);
		   service.shutdown();
	}
}
