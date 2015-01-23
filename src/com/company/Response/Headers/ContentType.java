package com.company.Response.Headers;

import com.company.Routes;

import java.util.HashMap;
import java.util.Map;

public class ContentType {
    public String getContentType(String filePath) {
        Map<String, String> routes = new HashMap<String, String>();
        
        routes.put(Routes.jpegRoute(), "image/jpeg" + "\r\n");
        routes.put(Routes.pngRoute(), "image/png" + "\r\n");
        routes.put(Routes.gifRoute(), "image/gif" + "\r\n");
        routes.put(Routes.formRoute(), "application/x-www-form-urlencoded" + "\r\n");
        
        for (Map.Entry<String, String> route : routes.entrySet()) {
            if (filePath.equals(route.getKey())) {
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