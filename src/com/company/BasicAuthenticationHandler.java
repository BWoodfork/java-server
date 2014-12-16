package com.company;

import sun.misc.BASE64Encoder;

import java.io.IOException;

public class BasicAuthenticationHandler {

    public byte[] authenticate(String data) throws IOException {
        String connection = "Connection:";
        String authenticationRequired = "Authentication required";
        String logs = "GET /log HTTP/1.1\r\n" + "PUT /these HTTP/1.1\r\n" + "HEAD /requests HTTP/1.1\r\n";

        if (data.equals(authenticationCredentials() + connection)) {
            return logs.getBytes();
        }
            return authenticationRequired.getBytes();
    }

    public String authenticationCredentials() throws IOException {
        String username = "admin";
        String password = "hunter2";

        byte[] encodedPassword = (username + ":" + password).getBytes();
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(encodedPassword);
    }
}