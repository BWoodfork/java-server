package com.company.Response;

import com.company.Handler.BasicAuthenticationHandler;
import com.company.Handler.PartialContentHandler;
import com.company.Utilities.FileMatcher;

import java.io.IOException;

public class HTTPStatusCodes {
    private String statusCode;

    public void setStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public byte[] getStatus() {
        String status = "HTTP/1.1 " + this.statusCode;
        return status.getBytes();
    }
    
    public byte[] setStatus() {
        String status = "HTTP/1.1 " + statusCode;
        return status.getBytes();
    }

    
    private FileMatcher fileMatcher;
    private BasicAuthenticationHandler basicAuthenticationHandler;

    public HTTPStatusCodes() {
//        fileMatcher = new FileMatcher();
        basicAuthenticationHandler = new BasicAuthenticationHandler();
    }

    public static String twoHundredOk = "200 OK";
    public static String fourOhOne = "401 Unauthorized";
    public static String twoOhSix = "206 Partial Content";
    public static String twoOhFour = "204 No Content";
    public static String fourOhFour = "404 NOT FOUND";
    public static String threeOhOne = "301 Moved Permanently";
    public static String fourOhFiveNotAllowed = "405 Method Not Allowed";

//    pass get in and determine what method it is. If is a get method, then pass request to get handler that will check the path for these getRoutes

    public String getHTTPStatusCode(String method, String requestPath, String data, String byteCount) throws IOException {
        if (method.equals("GET") && requestPath.equals(fileMatcher.matchRequestedFile(requestPath))) {
            return twoHundredOk;
        } else if (!method.equals("GET") && requestPath.equals(fileMatcher.matchRequestedFile(requestPath))) {
            return fourOhFiveNotAllowed;
        } else if (method.equals("OPTIONS") && requestPath.equals("/method_options")) {
            return twoHundredOk;
        } else if (method.equals("GET") && requestPath.equals("/form")) {
            return twoHundredOk;
        } else if (method.equals("GET") && requestPath.equals("/")) {
            return twoHundredOk;
        } else if (method.equals("POST") && requestPath.equals("/form")) {
            return twoHundredOk;
        } else if (method.equals("GET") && requestPath.equals("/form")) {
            return twoHundredOk;
        } else if (method.equals("PUT") && requestPath.equals("/form")) {
            return twoHundredOk;
        } else if (method.equals("DELETE") && requestPath.equals("/form")) {
            return twoHundredOk;
        } else if (method.equals("GET") && requestPath.equals("/logs") && !basicAuthenticationHandler.hasCredentials(data)) {
            return fourOhOne;
        } else if (method.equals("GET") && requestPath.equals("/logs") && basicAuthenticationHandler.hasCredentials(data)) {
            return twoHundredOk;
        } else if (method.equals("GET") && requestPath.equals("/redirect")) {
            return threeOhOne;
        } else if (method.equals("GET") && PartialContentHandler.isAPartialRequest(byteCount)) {
            return twoOhSix;
        } else if (method.equals("GET") && requestPath.equals("/patch-content.txt")) {
            return twoHundredOk;
        } else if (method.equals("PATCH")) {
            return twoOhFour;
        } else if (method.equals("POST") && requestPath.equals("/text-file.txt")) {
            return fourOhFiveNotAllowed;
        } else
            return fourOhFour;
    }

    public byte[] getStatusMessage(String method, String path, String data, String byteCount) throws IOException {
        String message =  "HTTP/1.1 " + getHTTPStatusCode(method, path, data, byteCount);
        return message.getBytes();
    }
}