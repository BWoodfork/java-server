package com.company.Reponse;

import com.company.Handler.Routes;

public class ContentType {
    public String getContentType(String requestParser) {
        if (requestParser.equals(Routes.jpegRoute())) {
            return "image/jpeg" + "\r\n";
        } else if (requestParser.equals(Routes.pngRoute())) {
            return "image/png" + "\r\n";
        } else if (requestParser.equals(Routes.gifRoute())) {
            return "image/gif\r\n";
        } else if (requestParser.equals(Routes.formRoute())) {
            return "application/x-www-form-urlencoded\r\n";
        }
        return "text/html\r\n";
    }

    public byte[] contentTypeResponse(String filePath) {
        String type = "Content-Type: " + getContentType(filePath);
        return type.getBytes();
    }
}