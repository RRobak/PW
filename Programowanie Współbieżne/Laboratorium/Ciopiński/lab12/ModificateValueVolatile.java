package lab12;

public class ModificateValueVolatile {
private volatile long value;

public long getValue() {
	return value;
}

public void increaseValue() {
	this.value++;
}
public void decreaseValue() {
	this.value--;
}
}
