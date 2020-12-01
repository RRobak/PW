class Vector {
	final private int x, y, z;
	final public static Vector UNIT_VECTOR_X = new Vector(1, 0, 0);
	final public static Vector UNIT_VECTOR_Y = new Vector(0, 1, 0);
	final public static Vector UNIT_VECTOR_Z = new Vector(0, 0, 1);

	private Vector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static Vector newInstance(int x, int y, int z) {
		if (x == 1 && y == 0 && z == 0)
			return UNIT_VECTOR_X;
		if (x == 0 && y == 1 && z == 0)
			return UNIT_VECTOR_Y;
		if (x == 0 && y == 0 && z == 1)
			return UNIT_VECTOR_Z;
		return new Vector(x, y, z);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Vector add(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
	}
	
	@Override
	public String toString(){
		return "[" + x + "," + y + "," + z + "]";
	}
}

public class TestImmutable {

	public static void main(String[] args) {
		Vector v = Vector.newInstance(1, 1, 1);
		v = v.add(Vector.newInstance(-1, -1, -1));
		System.out.println(v);
	}
}
