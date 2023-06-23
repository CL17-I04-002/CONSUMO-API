package com.consumo.api.util;

public class Endpoints {
    private static final String BASE_URL = "http://localhost:8080/";
    private static final String AUTHENTICATION_URL = BASE_URL + "api/v1/authenticate";
    private static final String HELLO_URL = BASE_URL + "api/v1/hello";
    private static final String ContentType = "application/json";

    public static String getBASE_URL() {
        return BASE_URL;
    }
    public static String getAUTHENTICATION_URL() {
        return AUTHENTICATION_URL;
    }

    public static String getHELLO_URL() {
        return HELLO_URL;
    }

    public static String getContentType() {
        return ContentType;
    }
}
