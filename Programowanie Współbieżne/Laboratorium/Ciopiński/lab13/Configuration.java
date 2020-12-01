package lab13;

public class Configuration {

	private static final float probabilityOfFailure=0.3f;
	private static final int inhibitantTimeSleep=20;
	private static final int maxRepairTime=5;
	private static final int inhibitantNumber=10;

	public static float getProbabilityoffailure() {
		return probabilityOfFailure;
	}

	public static int getInhibitanttimesleep() {
		return inhibitantTimeSleep;
	}

	public static int getMaxrepairtime() {
		return maxRepairTime;
	}

	public static int getInhibitantnumber() {
		return inhibitantNumber;
	}
	
}
