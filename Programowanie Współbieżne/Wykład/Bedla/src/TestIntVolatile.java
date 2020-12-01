public class TestIntVolatile {
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				while (true)
					Test.one();
			}
		}.start();
		new Thread() {
			public void run() {
				while (true)
					Test.two();
			}
		}.start();
	}

	private static class Test {
		private volatile static int i = 0;
		private volatile static int j = 0;

		private static void one() {
			i++;
			j++;
		}

		private static void two() {
			int tmpI = i;
			int tmpJ = j;
			System.out.println("" + tmpI + ";" + tmpJ);
			if (tmpI > tmpJ + 1)
				System.exit(1);
		}
	}
}
