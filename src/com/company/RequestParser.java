package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RequestParser {
    private String value;
    private FileResponse file;

    public RequestParser(String therequest) {
        this.value = therequest;
        file = new FileResponse();
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

    public String routeFile1() throws IOException {
        try {
            if (getFilePath().equals("/file1")) {
                return file.getFile();
            } else if (getFilePath().equals("/")) {
                return "<html><body>Hello World</body></html>";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "The Force is not strong with this one.";
    }
}