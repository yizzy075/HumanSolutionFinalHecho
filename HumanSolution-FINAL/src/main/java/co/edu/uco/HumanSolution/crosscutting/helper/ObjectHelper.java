package co.edu.uco.HumanSolution.crosscutting.helper;

public class ObjectHelper {

    private ObjectHelper() {
        super();
    }

    public static <T> boolean isNull(T object) {
        return object == null;
    }

    public static <T> T getDefault(T object, T defaultValue) {
        return isNull(object) ? defaultValue : object;
    }
}