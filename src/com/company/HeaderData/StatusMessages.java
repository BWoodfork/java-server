package com.company.HeaderData;

import com.company.HTTPResponse;
import com.company.RequestParser;

public class StatusMessages {
    private HTTPResponse httpResponse;

    public StatusMessages() {
        httpResponse = new HTTPResponse();
    }

    public byte[] getStatusMessage(RequestParser requestParser) {
        String filepath = requestParser.getFilePath();

        String status = "HTTP/1.1 " + httpResponse.getResponseStatus(filepath) + "\r\n";
        return status.getBytes();
    }
}