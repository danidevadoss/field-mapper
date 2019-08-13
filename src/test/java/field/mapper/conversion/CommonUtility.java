package field.mapper.conversion;

public class CommonUtility {

	public static boolean ynToBoolean(String string) {
		if ("Y".equals(string)) {
			return true;
		}
		return false;
	}

	public static String booleanToYn(boolean isY) {
		if (isY) {
			return "Y";
		}
		return "N";
	}

}
