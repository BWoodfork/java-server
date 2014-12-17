package com.company.HeaderData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class StreamHandler {
    private RequestBuilder requestBuilder;

    public StreamHandler() {
        requestBuilder = new RequestBuilder();
    }

    public BufferedReader getInputStream(Socket socket) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        return new BufferedReader(inputStreamReader);
    }

    public String getInputStreamStringValue(Socket socket) throws IOException {
        return requestBuilder.getRequestString(getInputStream(socket));
    }
}