package com.company.Parser;

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
    
    public String getByteCount() {
        return parseRequest()[3];
    }

    public String getData() {
        return parseRequest()[4];
    }
}