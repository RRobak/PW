package lab12;

public final class Rectangle implements Comparable<Rectangle> {

	private final int posX;
	private final int posY;
	private final int height;
	private final int width;
	private final int angle;

	private Rectangle(int posX, int posY, int height, int width, int angle) {
		this.posX = posX;
		this.posY = posY;
		this.height = height;
		this.width = width;
		this.angle = angle;
	}

	public static Rectangle getInstance(int posX, int posY, int height,
			int width, int angle) {
		return new Rectangle(posX, posY, height, width, angle);
	}

	public Rectangle changeAll(Rectangle rectangle) {

		return new Rectangle(this.posX + rectangle.posX, this.posY
				+ rectangle.posY, this.height + rectangle.height, this.width
				+ rectangle.width, this.angle + rectangle.angle);

	}

	public Rectangle changePosX(int arg) {

		return new Rectangle(arg, posY, height, width, angle);

	}

	public Rectangle changePosY(int arg) {

		return new Rectangle(posX, arg, height, width, angle);

	}

	public Rectangle changeHeight(int arg) {

		return new Rectangle(posX, posY, arg, width, angle);

	}

	public Rectangle changeWidth(int arg) {
		return new Rectangle(posX, posY, height, arg, angle);

	}

	public Rectangle rotate90() {
		if (angle + 90 == 360)
			return new Rectangle(posX, posY, height, width, 0);
		else
			return new Rectangle(posX, posY, height, width, angle + 90);

	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + angle;
		result = prime * result + height;
		result = prime * result + posX;
		result = prime * result + posY;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		if (angle != other.angle)
			return false;
		if (height != other.height)
			return false;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rectangle [posX=" + posX + ", posY=" + posY + ", height="
				+ height + ", width=" + width + ", angle=" + angle + "]";
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public int compareTo(Rectangle arg) {
		if (this.width * this.height < arg.height * arg.width)
			return -1;
		else if (this.width * this.height == arg.height * arg.width)
			return 0;
		else
			return 1;
	}

	public int getAngle() {
		return angle;
	}

}
