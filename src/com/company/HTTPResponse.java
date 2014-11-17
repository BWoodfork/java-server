package com.company;

public class HTTPResponse {
    public String getResponseStatus(String path) {
        if (path.equals("/foobar")) {
            return "404 NOT FOUND";
        } else {
            return "200 OK";
        }
    }
}