package co.edu.uco.HumanSolution.crosscutting.helper;

import java.util.UUID;

public class UUIDHelper {

    private static final String DEFAULT_UUID_STRING = "00000000-0000-0000-0000-000000000000";

    private UUIDHelper() {
        super();
    }

    public static UUID getDefault(UUID value, UUID defaultValue) {
        return ObjectHelper.getDefault(value, defaultValue);
    }

    public static UUID getDefault(UUID value) {
        return getDefault(value, generateNewUUID());
    }

    public static UUID generateNewUUID() {
        return UUID.randomUUID();
    }

    public static UUID getDefaultUUID() {
        return UUID.fromString(DEFAULT_UUID_STRING);
    }

    public static String getDefaultUUIDAsString() {
        return DEFAULT_UUID_STRING;
    }

    public static boolean isDefault(UUID value) {
        return getDefaultUUID().equals(getDefault(value, getDefaultUUID()));
    }

    public static UUID generate() {
        return UUID.randomUUID();
    }

    public static UUID fromString(String uuidAsString) {
        try {
            return UUID.fromString(uuidAsString);
        } catch (Exception exception) {
            return getDefaultUUID();
        }
    }
}