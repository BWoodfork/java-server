package com.company.HeaderData;

import Reponse.HTTPResponse;

public class StatusMessages {
    private HTTPResponse httpResponse;

    public StatusMessages() {
        httpResponse = new HTTPResponse();
    }

    public byte[] getStatusMessage(String method, String requestPath, String data) {
        String status = "HTTP/1.1 " + httpResponse.getHTTPStatusCode(method, requestPath, data) + "\r\n";
        return status.getBytes();
    }
}