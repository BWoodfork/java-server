package com.company.HeaderData;

public class ContentType {
    public String getContentType(String requestParser) {
        if (requestParser.equals("/image.jpeg")) {
            return "image/jpeg\r\n";
        } else if (requestParser.equals("/image.png")) {
            return "image/png\r\n";
        } else if (requestParser.equals("/image.gif")) {
            return "image/gif\r\n";
        } else if (requestParser.equals("/form")) {
            return "application/x-www-form-urlencoded\r\n";
        }
        return "text/html\r\n";
    }
}