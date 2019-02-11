package core.utils;

public class PetUtils {
    private PetUtils(){}

    public static String generateUniqueInternalCode(String petType, String petGender, Long code) {
        return petType + petGender + code.toString();
    }

    public static Long extractNumber(String code) {
        code = code.replaceAll("[^-?0-9]+", " ");
        return Long.valueOf(code.trim());
    }
}
