package com.company.Response.Headers;

import com.company.Utilities.FileMatcher;

public class AllowMethods {
    private FileMatcher fileMatcher;
    
    public AllowMethods(FileMatcher fileMatcher) {
        this.fileMatcher = fileMatcher;
    }
    
    public String constructAllowHeader(String method, String requestPath) {
        if (method.equals("GET") && requestPath.equals(fileMatcher.matchRequestedFile(requestPath))) {
            return "Allow: GET\r\n";
        } else if (method.equals("OPTIONS") && requestPath.equals("/method_options")) {
            return "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n";
        } else 
            return "Allow:\r\n";
    }
    
    public byte[] getAllowResponse(String method, String requestPath) {
        return constructAllowHeader(method, requestPath).getBytes();
    }
}