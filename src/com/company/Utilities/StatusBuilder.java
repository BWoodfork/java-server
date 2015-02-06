package com.company.Utilities;

import java.util.HashMap;

public class StatusBuilder {
    private Integer key;
    
    private HashMap<Integer, String> httpStatus() {
        HashMap<Integer, String> statusSet = new HashMap<>();
        statusSet.put(200, "200 OK");
        statusSet.put(404, "404 NOT FOUND");
        statusSet.put(401, "401 Unauthorized");
        statusSet.put(301, "301 Moved Permanently");
        statusSet.put(206, "206 Partial Content");
        statusSet.put(204, "204 No Content");
        return statusSet;
    }
    
    public void setHTTPStatus(Integer key) {
        this.key = key;
    }
    
    public byte[] getHTTPStatus() {
        String statusString = "HTTP/1.1 " + httpStatus().get(key) + "\r\n";
        return statusString.getBytes();
    }
}