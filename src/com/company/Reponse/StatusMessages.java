package com.company.Reponse;

public class StatusMessages {
    private HTTPStatusCodes httpStatusCodes;

    public StatusMessages() {
        httpStatusCodes = new HTTPStatusCodes();
    }

    public byte[] getStatusMessage(String method, String requestPath, String data) {
        String status = "HTTP/1.1 " + httpStatusCodes.getHTTPStatusCode(method, requestPath, data) + "\r\n";
        return status.getBytes();
    }
}