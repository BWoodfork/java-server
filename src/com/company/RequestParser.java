package com.company;

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

//    public byte[] routeFile1() throws IOException {
//        try {
//            if (Arrays.equals(getFilePath(), getFileBytes())) {
//                return file.getFile();
//            } else if (Arrays.equals(getFilePath(), getEmptyPath())) {
//                return getHelloWorldMessage();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
////        return "The Force is not strong with this one.";
//        return null;
//    }
}