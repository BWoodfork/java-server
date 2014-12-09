package com.company;

public class HTTPResponse {
    public String getResponseStatus(String path) {
        if (path.equals("/file1")) {
            return "405 OK";
        } else if (path.equals("/")) {
            return "200 OK";
        } else if (path.equals("/method_options")) {
            return "200 OK";
        } else if (path.equals("/form")) {
            return "200 OK";
        } else if (path.equals("/file2")) {
            return "200 OK";
        } else if (path.equals("/logs")) {
            return "401 Unauthorized";
        } else if (path.equals("/text-file.txt")) {
            return "405 Method Not Allowed";
        } else if (path.equals("/log")) {
            return "200 OK";
        } else if (path.equals("/redirect")) {
            return "200 OK";
        } else if (path.equals("/partial_content.txt")) {
            return "206 Partial Content";
        }
        return "404 NOT FOUND";
    }
}