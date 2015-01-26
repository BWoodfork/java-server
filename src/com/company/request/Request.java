package com.company.request;

import java.io.IOException;

public class Request {
    private String request;
    
    public Request(String request) {
        this.request = request;
    }

    public String[] parseRequest() throws IOException {
        return request.split(" ");
    }
    
    public String getMethod() throws IOException {
        System.out.println(request);
        return parseRequest()[0];
    }


    public String getFilePath() throws IOException {
        return parseRequest()[1];
    }

    public String getByteCount() throws IOException {
        return parseRequest()[3];
    }

    public String getData() throws IOException {
        return parseRequest()[4];
    }
}