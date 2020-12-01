package lab14.zad4;

import java.util.ArrayList;

public class ToiletTest {
public static void main(String[] args) throws InterruptedException {
	ArrayList<Cabin> cabins = new ArrayList<Cabin>();
	for (int i = 0; i < 4; i++)
		cabins.add(new Cabin());
	Toilet toilet = new Toilet(cabins);
	System.out.println("Wolne miejsca: "+toilet.getAvailable());
	toilet.go();
	System.out.println("Wolne miejsca: "+toilet.getAvailable());
	toilet.go();
	System.out.println("Wolne miejsca: "+toilet.getAvailable());
	toilet.go();
	System.out.println("Wolne miejsca: "+toilet.getAvailable());
	toilet.go();
	System.out.println("Wolne miejsca: "+toilet.getAvailable());
	toilet.out();
	System.out.println("Wolne miejsca: "+toilet.getAvailable());
	toilet.out();
	System.out.println("Wolne miejsca: "+toilet.getAvailable());
	toilet.out();
	System.out.println("Wolne miejsca: "+toilet.getAvailable());
	
}
}
