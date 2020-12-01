package lab12;

public class RectangleTest {
	public static void main(String[] args) {

		Rectangle rectangle = Rectangle.getInstance(0, 0, 0, 0, 0);
		System.out.println(rectangle);
		rectangle = rectangle.changeHeight(100);
		rectangle = rectangle.changeWidth(50);
		rectangle = rectangle.changePosX(10);
		rectangle = rectangle.changePosY(50);
		System.out.println(rectangle);
		rectangle=rectangle.rotate90();
		rectangle=rectangle.rotate90();
		rectangle=rectangle.rotate90();
		System.out.println(rectangle);
		
	}

}
