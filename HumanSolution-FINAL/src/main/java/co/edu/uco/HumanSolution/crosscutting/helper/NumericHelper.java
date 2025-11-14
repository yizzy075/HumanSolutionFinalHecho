package co.edu.uco.HumanSolution.crosscutting.helper;

public final class NumericHelper {

    private static final byte ZERO = 0;
    private static final double ZERO_DOUBLE = 0.0;

    private NumericHelper() {
        super();
    }

    public static <T extends Number> boolean isNull(T value) {
        return ObjectHelper.isNull(value);
    }

    public static <T extends Number> T getDefault(T value, T defaultValue) {
        return ObjectHelper.getDefault(value, defaultValue);
    }

    public static <T extends Number> T getDefault(T value) {
        return getDefault(value, null);
    }

    public static byte getDefaultZero(Byte value) {
        return getDefault(value, ZERO);
    }

    public static short getDefaultZero(Short value) {
        return getDefault(value, (short) ZERO);
    }

    public static int getDefaultZero(Integer value) {
        return getDefault(value, (int) ZERO);
    }

    public static long getDefaultZero(Long value) {
        return getDefault(value, (long) ZERO);
    }

    public static float getDefaultZero(Float value) {
        return getDefault(value, (float) ZERO_DOUBLE);
    }

    public static double getDefaultZero(Double value) {
        return getDefault(value, ZERO_DOUBLE);
    }

    public static boolean isLessThan(int value, int minimum) {
        return value < minimum;
    }

    public static boolean isLessThanOrEqual(int value, int minimum) {
        return value <= minimum;
    }

    public static boolean isGreaterThan(int value, int maximum) {
        return value > maximum;
    }

    public static boolean isGreaterThanOrEqual(int value, int maximum) {
        return value >= maximum;
    }

    public static boolean isBetween(int value, int minimum, int maximum) {
        return !isLessThan(value, minimum) && !isGreaterThan(value, maximum);
    }

    public static boolean isPositive(int value) {
        return value > ZERO;
    }

    public static boolean isNegative(int value) {
        return value < ZERO;
    }

    public static boolean isZero(int value) {
        return value == ZERO;
    }
}

