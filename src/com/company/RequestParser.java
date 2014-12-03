package com.company;

public class RequestParser {
    private String value;

    public RequestParser(String therequest) {
        this.value = therequest;
    }

    public String[] parseRequest() {
        return value.split(" ");
    }

    public String getMethod() {
        return parseRequest()[0];
    }

    public String getFilePath() {
        return parseRequest()[1];
    }
}