package co.edu.uco.HumanSolution.crosscutting.helper;

public class TextHelper {

    public static final String EMPTY = "";
    public static final String UNDERLINE = "_";

    private TextHelper() {
        super();
    }

    public static boolean isNull(String string) {
        return ObjectHelper.isNull(string);
    }

    public static String getDefault(String string, String defaultValue) {
        return ObjectHelper.getDefault(string, defaultValue);
    }

    public static String getDefault(String string) {
        return getDefault(string, EMPTY);
    }

    public static boolean isEmpty(String string) {
        return EMPTY.equals(getDefault(string));
    }

    public static boolean isEmptyApplyingTrim(String string) {
        return isEmpty(applyTrim(string));
    }

    public static String applyTrim(String string) {
        return getDefault(string).trim();
    }

    public static int len(String string) {
        return applyTrim(string).length();
    }
}