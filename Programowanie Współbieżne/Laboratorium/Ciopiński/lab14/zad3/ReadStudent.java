package lab14.zad3;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ReadStudent extends Student{

	public ReadStudent(Exchanger<Paper> exchanger, Paper initialPaper) {
		super(exchanger, initialPaper);
		
	}

	@Override
	void work(Paper paper) throws InterruptedException {
		TimeUnit.SECONDS.sleep(r.nextInt(10));
		paper.clear = true;
		System.out.println(paper);
		
	}

}
