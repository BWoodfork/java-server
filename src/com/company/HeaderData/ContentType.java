package com.company.HeaderData;

import com.company.RequestParser;

public class ContentType {
    public String getContentType(RequestParser requestParser) {
        if (requestParser.getFilePath().equals("/image.jpeg")) {
            return "image/jpeg\r\n";
        } else if (requestParser.getFilePath().equals("/image.png")) {
            return "image/png\r\n";
        } else if (requestParser.getFilePath().equals("/image.gif")) {
            return "image/gif\r\n";
        }
        return "text/html\r\n";
    }
}
