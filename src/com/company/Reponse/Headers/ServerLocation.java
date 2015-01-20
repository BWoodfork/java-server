package com.company.Reponse.Headers;

public class ServerLocation {
    public byte[] getLocationResponse(int port) {
        String location = "Location: http://localhost:" + String.valueOf(port) + "/";
        String locationHeader = location + "\r\n";

        return locationHeader.getBytes();
    }
}