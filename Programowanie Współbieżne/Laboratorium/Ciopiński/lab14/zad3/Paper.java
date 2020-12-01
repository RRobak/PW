package lab14.zad3;

public class Paper {
	volatile boolean clear=true;
	private int number;
	
	public Paper(int number) {
		this.number=number;
	}

	@Override
	public String toString() {
		return "Kartka " + number + ": " + (clear ? "Czysta" : "Zapisana");
	}
}
