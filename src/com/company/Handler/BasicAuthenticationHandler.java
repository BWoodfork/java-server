package com.company.Handler;

import sun.misc.BASE64Encoder;

import java.io.IOException;

public class BasicAuthenticationHandler {
    
    public String encodeAuthenticationCredentials() throws IOException {
        String username = "admin";
        String password = "hunter2";

        byte[] encodedPassword = (username + ":" + password).getBytes();
        BASE64Encoder encoder = new BASE64Encoder();
        
        return encoder.encode(encodedPassword);
    }

    public String parseAuthenticationData() throws IOException {
        String logs = "GET /log HTTP/1.1\r\n" + "PUT /these HTTP/1.1\r\n" + "HEAD /requests HTTP/1.1\r\n";

        return logs;
    }
}