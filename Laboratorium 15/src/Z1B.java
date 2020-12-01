import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Z1B {

    public static void main(String[] args) {
        Integer[] tab = FillArray(5);
        List<Integer> longs = asList(tab);
        System.out.println("\nTablica:");
        for (int i = 0; i < 5; i++) {
            System.out.println(tab[i]);
        }
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<List<Integer>> task = new MergeTask(longs);
        List<Integer> result = pool.invoke(task);
        pool.shutdown();
        System.out.println(result);
        if (CheckIfSorted(result)) {
            System.out.println("\nTablica została posortowana\n");
        } else {
            System.out.println("\nTablica nie zostala posortowana\n");
        }
    }

    public static class MergeTask extends RecursiveTask<List<Integer>> {

        private static final int pkt = 2;
        private final List<Integer> x;

        public MergeTask(List<Integer> list) {
            this.x = list;
        }

        @Override
        protected List<Integer> compute() {
            if (x.size() < pkt) {
                return x.stream().sorted().collect(toList());
            }
            MergeTask left = new MergeTask(
                    x.stream().limit(x.size() / 2).collect(toList())
            );
            MergeTask right = new MergeTask(
                    x.stream().skip(x.size() / 2).collect(toList())
            );
            invokeAll(left, right);
            return merge(left.join(), right.join());
        }
    }

    public static List<Integer> merge(List<Integer> a, List<Integer> b) {
        int i = 0, j = 0;
        List<Integer> result = new ArrayList<>(a.size() + b.size());
        while (i < a.size() && j < b.size()) result.add(
                a.get(i) < b.get(j) ? a.get(i++) : b.get(j++)
        );
        while (i < a.size()) {
            result.add(a.get(i++));
        }
        while (j < b.size()) {
            result.add(b.get(j++));
        }
        return result;
    }

    static boolean CheckIfSorted(List<Integer> array) {
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i - 1) > array.get(i)) return false;
        }
        return true;
    }

    public static Integer[] FillArray(int length) {
        assert length > 0;
        Random random = new Random(length);
        Integer[] array = new Integer[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.valueOf(random.nextInt(69));
        }
        return array;
    }
}
