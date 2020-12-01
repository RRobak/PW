import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestCollections2 {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>(new Integer(10));
		set.addAll(Arrays.asList(new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }));

		for (Iterator<Integer> it = set.iterator(); it.hasNext();)
			if (it.next() % 2 == 1)
				it.remove();

		for (Integer i : set)
			System.out.print(i + ", ");

	}
}