package com.company.Reponse.Headers;

public class BodyLength {
    public byte[] getBodyLength(byte[] body) {
        String length = "Content-Length: " + Integer.toString(body.length) + "\r\n\r\n";
        return length.getBytes();
    }
}