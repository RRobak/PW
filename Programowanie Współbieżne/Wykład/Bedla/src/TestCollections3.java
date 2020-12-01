import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestCollections3 {

	public static void main(String[] args) {
		Set<Integer> collection = new HashSet<Integer>(Arrays.asList(new Integer[] { 1, 2, 3 }));

		Collection<Integer> unmodifiableCollection = Collections.unmodifiableCollection(collection);

		System.out.println("UnmodifiableCollection " + unmodifiableCollection);
		System.out.println("            Collection " + collection);

		try {
			Iterator<Integer> it = unmodifiableCollection.iterator();
			while (it.hasNext()) {
				it.next();
				it.remove();
			}
		} catch (Exception e) {
			System.out.println("UnmodifiableCollection: Exception");
			e.printStackTrace(System.out);
		}

		try {
			Iterator<Integer> it = collection.iterator();
			while (it.hasNext()) {
				it.next();
				it.remove();
			}
		} catch (Exception e) {
			System.out.println("            Collection: Exception");
			e.printStackTrace();
			System.out.println("UnmodifiableCollection " + unmodifiableCollection);
			System.out.println("            Collection " + collection);
		}
	}
}