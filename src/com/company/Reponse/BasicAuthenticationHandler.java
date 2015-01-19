package com.company.Reponse;

import sun.misc.BASE64Decoder;
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
    
    public String decodeAuthenticationCredentials() throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(encodeAuthenticationCredentials()));
    }

    public String parseAuthenticationData(String data) throws IOException {
        String connection = "Connection:";
        String authenticationRequired = "Authentication required";
        String logs = "GET /log HTTP/1.1\r\n" + "PUT /these HTTP/1.1\r\n" + "HEAD /requests HTTP/1.1\r\n";

        if (data.equals(encodeAuthenticationCredentials() + connection)) {
            return logs;
        }
            return authenticationRequired;
    }

    public byte[] getAuthenticationData(String data) throws IOException {
        return parseAuthenticationData(data).getBytes();
    }
}