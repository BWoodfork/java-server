package com.company.HeaderData;

public class AllowMethods {
    public byte[] getAllowResponse() {
        String allow = "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n";
        return allow.getBytes();
    }
}