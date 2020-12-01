public class TestLongVolatile {
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				while (true)
					TestLong.write();
			}
		}.start();
		new Thread() {
			public void run() {
				while (true)
					TestLong.read();
			}
		}.start();
	}

	private static class TestLong {

		static volatile long i = 0;

		public static void write() {
			long tmp = i & 0xFFFFFFFFL;
			tmp++;
			i = tmp | (tmp << 32);
		}

		public static void read() {
			long tmp = i;
			long l = tmp & 0xFFFFFFFFL;
			long h = tmp >>> 32;
			if (l != h) {
				System.err.println("!" + Long.toHexString(tmp) + " : " + h + " != " + l);
				System.exit(0);
			}
		}
	}
}