package com.company.Response.Headers;

import java.util.HashMap;
import java.util.Map;

public class ContentType {
    
    public static String JPEG = "/image.jpeg";
    public static String PNG = "/image.png";
    public static String GIF = "/image.gif";
    public static String FORM = "/form";
    
    public String getContentType(String filePath) {
        Map<String, String> routes = new HashMap<String, String>();
        
        routes.put(JPEG, "image/jpeg" + "\r\n");
        routes.put(PNG, "image/png" + "\r\n");
        routes.put(GIF, "image/gif" + "\r\n");
        routes.put(FORM, "application/x-www-form-urlencoded" + "\r\n");
        
        for (Map.Entry<String, String> route : routes.entrySet()) {
            if (filePath.endsWith(route.getKey())) {
                return route.getValue();
            }
        }

        return "text/html" + "\r\n";
    }

    public byte[] getContentTypeHeader(String filePath) {
        String type = "Content-Type: " + getContentType(filePath);
        return type.getBytes();
    }
}