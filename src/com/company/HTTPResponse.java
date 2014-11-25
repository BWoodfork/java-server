package com.company;

public class HTTPResponse {
    public String getResponseStatus(String path) {
        if (path.equals("/file1")) {
            return "200 OK";
        } else if (path.equals("/")) {
            return "200 OK";
        } else if (path.equals("/method_options")) {
            return "200 OK";
        } else if (path.equals("/form")) {
            return "200 OK";
        }
        return "404 NOT FOUND";
    }
}