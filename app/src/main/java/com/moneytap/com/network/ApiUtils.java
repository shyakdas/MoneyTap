package com.moneytap.com.network;

public class ApiUtils {

    private ApiUtils() {
    }

    private static APIService API_SERVICE;
    public static final String BASE_URL = "https://en.wikipedia.org/w/";

    public static APIService getAPIService() {
        if (API_SERVICE == null) {
            synchronized (ApiUtils.class) {
                if (API_SERVICE == null) {
                    API_SERVICE = RetrofitClient.getClient(BASE_URL).create(APIService.class);
                }
            }
        }
        return API_SERVICE;
    }
}