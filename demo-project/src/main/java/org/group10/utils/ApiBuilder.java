package org.group10.utils;

public class ApiBuilder {
    private String domainUrl;
    private String endpoint;
    private String parameter;

    public static String buildApiUrl(String domainUrl, String endpoint, String... parameters){
        String url = null;
        for (String param:parameters) {
            url = domainUrl + "\\" + endpoint + "\\" + param;
        }
        return url;
    }

}
