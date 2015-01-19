package com.company.Reponse.Headers;

public class ServerLocation {
    public byte[] getLocationResponse() {
        String location = "Location: http://localhost:5000/";
        String locationHeader = location + "\r\n";

        return locationHeader.getBytes();
    }
}