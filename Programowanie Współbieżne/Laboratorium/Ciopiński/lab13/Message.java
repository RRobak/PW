package lab13;

public class Message {
	private String desctiption;

	public Message(String description) {

		this.desctiption = description;
	}

	public String getDesctiption() {
		return desctiption;
	}

	@Override
	public String toString() {
		return "Message [desctiption=" + desctiption + "]";
	}

}
