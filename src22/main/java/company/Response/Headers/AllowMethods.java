package main.java.company.Response.Headers;

import main.java.company.Utilities.FileMatcher;
import main.java.company.Response.ResponseBuilders.StatusBuilder;

public class AllowMethods {
    private FileMatcher fileMatcher;
    
    public AllowMethods(FileMatcher fileMatcher) {
        this.fileMatcher = fileMatcher;
    }
    
    public String constructAllowHeader(String method, String requestPath, StatusBuilder statusBuilder) {
        if (method.equals("GET") && requestPath.equals(fileMatcher.matchRequestedFile(requestPath))) {
            return "Allow: GET\r\n";
        } else if (method.equals("OPTIONS") && requestPath.equals("/method_options")) {
            return "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n";
        } else if (method.equals("POST") && requestPath.equals(fileMatcher.matchRequestedFile(requestPath))) {
            return "Allow: GET,POST\r\n";
        } else if (method.equals("PUT") && requestPath.equals(fileMatcher.matchRequestedFile(requestPath))) {
            return "Allow: GET, PUT\r\n";
        } else if (method.equals("PATCH") && requestPath.equals(fileMatcher.matchRequestedFile(requestPath))) {
            return "Allow: GET, PATCH\r\n";
        }
            return "Allow:\r\n";
    }
    
    public byte[] getAllowResponse(String method, String requestPath, StatusBuilder statusBuilder) {
        return constructAllowHeader(method, requestPath, statusBuilder).getBytes();
    }
}