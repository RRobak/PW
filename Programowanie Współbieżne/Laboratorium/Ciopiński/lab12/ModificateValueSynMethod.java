package lab12;

public class ModificateValueSynMethod {
	private long value;

	public synchronized long getValue() {
		
			return value;
		

	}

	public synchronized void increaseValue() {
		
			this.value++;
		
	}

	public synchronized void decreaseValue() {
		
			this.value--;
		
	}
}
