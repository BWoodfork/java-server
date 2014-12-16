package com.company;

public class RequestParser {
    private String therequest;

    public RequestParser(String therequest) {
        this.therequest = therequest;
    }

    public String getFullRequest() {
        return therequest;
    }

    public String[] parseRequest() {
        return therequest.split(" ");
    }

    public String getMethod() {
        return parseRequest()[0];
    }

    public String getFilePath() {
        return parseRequest()[1];
    }

    public String getProtocol() {
        return parseRequest()[2];
    }

    public String getPartialRequest() {
        return parseRequest()[3];
    }

    public String getAuthenticationData() {
        return parseRequest()[4];
    }
}