package com.company.Handler;

import com.company.Response.RequestBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class StreamHandler {
    private RequestBuilder requestBuilder;

    public StreamHandler(RequestBuilder requestBuilder) {
       this.requestBuilder = requestBuilder;
    }

    public BufferedReader getInputStream(Socket socket) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        return new BufferedReader(inputStreamReader);
    }

    public String convertRawRequestToString(Socket socket) throws IOException {
        return requestBuilder.getRequestString(getInputStream(socket));
    }
}