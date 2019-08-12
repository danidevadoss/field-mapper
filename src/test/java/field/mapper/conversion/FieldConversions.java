package field.mapper.conversion;

/**
 * Class with sample conversion method
 */
public class FieldConversions {

    public static final String STRING_TO_INTEGER = "stringToInteger";
    public static final String INTEGER_TO_STRING = "integerToString";

    public static Integer stringToInteger(String string) {
        if (string != null && string.trim().length() != 1) {
            return Integer.parseInt(string);
        }
        return null;
    }

    public static String integerToString(Integer i) {
        return String.valueOf(i);
    }
}
