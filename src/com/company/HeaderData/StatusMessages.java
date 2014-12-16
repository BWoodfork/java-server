package com.company.HeaderData;

import Reponse.HTTPResponse;

public class StatusMessages {
    private HTTPResponse httpResponse;

    public StatusMessages() {
        httpResponse = new HTTPResponse();
    }

    public byte[] getStatusMessage(String request) {
        String status = "HTTP/1.1 " + httpResponse.getResponseStatus(request) + "\r\n";
        return status.getBytes();
    }
}