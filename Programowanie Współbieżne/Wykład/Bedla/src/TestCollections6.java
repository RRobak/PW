import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestCollections6 {
	public static void main(String[] args) {
		List<Integer> list = new CopyOnWriteArrayList<Integer>();
		list.add(1);
		list.add(2);

		try {
			Iterator<Integer> it = list.iterator();
			while (it.hasNext()) {
				System.out.print(it.next() + ", ");
				list.add(3);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println();

		for (int i : list)
			System.out.print(i + ", ");
	}
}