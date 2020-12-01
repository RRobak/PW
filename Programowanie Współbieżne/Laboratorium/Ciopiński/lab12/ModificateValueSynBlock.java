package lab12;

public class ModificateValueSynBlock {
	private long value;

	public long getValue() {
		synchronized (this) {
			return value;
		}

	}

	public void increaseValue() {
		synchronized (this) {
			this.value++;
		}

	}

	public void decreaseValue() {
		synchronized (this) {
			this.value--;
		}
	}
}
