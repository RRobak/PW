package lab12;

public class ModificateValueWaitNofify {
	private long value;

	public synchronized long getValue() {
		
			return value;
		

	}

	public synchronized void increaseValue() {
			if (value>0)
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			this.value++;
			notify();
		
	}

	public synchronized void decreaseValue() {
			if (value<=0)
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			this.value--;
			notify();
		
	}
}
