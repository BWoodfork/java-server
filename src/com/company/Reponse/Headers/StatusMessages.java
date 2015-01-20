package com.company.Reponse.Headers;

import com.company.Reponse.HTTPStatusCodes;

public class StatusMessages {
    private HTTPStatusCodes httpStatusCodes;

    public StatusMessages() {
        httpStatusCodes = new HTTPStatusCodes();
    }

    public byte[] getStatusMessage(String method, String path, String data) {
        String status = "HTTP/1.1 " + httpStatusCodes.getHTTPStatusCode(method, path, data) + "\r\n";
        return status.getBytes();
    }
}