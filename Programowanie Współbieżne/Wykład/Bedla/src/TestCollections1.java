import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestCollections1 {
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>(new Integer(10));
		set.addAll(Arrays.asList(new Integer[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }));

		for (Integer i : set)
			if (i % 2 == 1)
				set.remove(i);

		for (Integer i : set)
			System.out.print(i + ", ");

	}
}