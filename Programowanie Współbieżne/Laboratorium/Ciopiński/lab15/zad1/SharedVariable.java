package lab15.zad1;

public class SharedVariable {
private static boolean cancel=false;

public static boolean isCancel() {
	return cancel;
}

public static void cancel() {
	cancel=true;
}


}
