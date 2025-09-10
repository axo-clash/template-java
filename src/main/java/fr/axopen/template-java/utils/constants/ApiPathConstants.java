package fr.axopen.template-java.utils.constants;

public final class ApiPathConstants {

    private ApiPathConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BASE_PATH = "/api/v1";
    public static final String ADMIN_PATH = BASE_PATH + "/admin";

}

