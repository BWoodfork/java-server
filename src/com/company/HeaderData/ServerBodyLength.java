package com.company.HeaderData;

public class ServerBodyLength {
    public byte[] getBodyLength(byte[] body) {
        String length = "Content-Length: " + Integer.toString(body.length) + "\r\n\r\n";
        return length.getBytes();
    }
}