package co.edu.uco.HumanSolution.crosscutting.helper;

public final class StringHelper {

    private StringHelper() {
        super();
    }

    public static boolean isNull(String string) {
        return ObjectHelper.isNull(string);
    }

    public static String getDefault(String string, String defaultValue) {
        return ObjectHelper.getDefault(string, defaultValue);
    }

    public static String getDefault(String string) {
        return TextHelper.getDefault(string);
    }

    public static boolean isEmpty(String string) {
        return TextHelper.isEmpty(string);
    }

    public static boolean isEmptyApplyingTrim(String string) {
        return TextHelper.isEmptyApplyingTrim(string);
    }

    public static String applyTrim(String string) {
        return TextHelper.applyTrim(string);
    }

    public static int length(String string) {
        return TextHelper.len(string);
    }

    public static boolean isNumeric(String string) {
        if (isEmptyApplyingTrim(string)) {
            return false;
        }
        try {
            Double.parseDouble(applyTrim(string));
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static boolean isInteger(String string) {
        if (isEmptyApplyingTrim(string)) {
            return false;
        }
        try {
            Integer.parseInt(applyTrim(string));
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static boolean containsOnlyLetters(String string) {
        if (isEmptyApplyingTrim(string)) {
            return false;
        }
        return applyTrim(string).matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    public static boolean containsOnlyLettersAndNumbers(String string) {
        if (isEmptyApplyingTrim(string)) {
            return false;
        }
        return applyTrim(string).matches("^[a-zA-Z0-9]+$");
    }

    public static boolean isEmailFormat(String string) {
        if (isEmptyApplyingTrim(string)) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return applyTrim(string).matches(emailRegex);
    }

    public static String replace(String text, String searchString, String replacement) {
        if (isEmpty(text) || isEmpty(searchString)) {
            return getDefault(text);
        }
        return text.replace(searchString, getDefault(replacement, TextHelper.EMPTY));
    }
}

