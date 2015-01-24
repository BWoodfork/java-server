package com.company.request;

import com.company.Handler.StreamHandler;
import com.company.Response.RequestBuilder;

import java.io.IOException;
import java.net.Socket;

public class Request implements RequestInterface {
    private RequestBuilder requestBuilder;
    
    public Request(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    @Override
    public String getRequest(Socket socket) throws IOException {
        StreamHandler streamHandler = new StreamHandler(requestBuilder);
        
        return streamHandler.convertRawRequestToString(socket);
    }
    
//    public RequestParser parseRequest(Socket socket) throws IOException {
//       return new RequestParser(getRequest(socket));
//    }
}