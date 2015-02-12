package main.java.company.Response.Headers;

import main.java.company.request.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ContentType {
    
    public static String JPEG = "/image.jpeg";
    public static String PNG = "/image.png";
    public static String GIF = "/image.gif";
    public static String FORM = "/form";
    
    public String parseRequestPath(String filePath) {
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

    public byte[] getContentType(Request request) throws IOException {
        String filePath = request.getFilePath();
        
        String type = "Content-Type: " + parseRequestPath(filePath);
        return type.getBytes();
    }
}