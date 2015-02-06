package com.company.Response.ResponseGenerators;

import com.company.Handler.BasicAuthenticationHandler;
import com.company.Routes.IResponse;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.io.IOException;

public class BasicAuthenticationIResponseGenerator implements IResponse {
    private BasicAuthenticationHandler basicAuthenticationHandler;
    private StatusBuilder statusBuilder;
    
    public BasicAuthenticationIResponseGenerator(StatusBuilder statusBuilder) {
        basicAuthenticationHandler = new BasicAuthenticationHandler();
        this.statusBuilder = statusBuilder;
    }
    
    public boolean hasCredentials(String data) throws IOException {
        return data.equals(basicAuthenticationHandler.encodeAuthenticationCredentials() + "Connection:");
    }
    
    public byte[] getBody(Request request) throws Exception {
        String data = request.getData();
        
        if (hasCredentials(data)) {
            statusBuilder.setHTTPStatus(200);
            return basicAuthenticationHandler.parseAuthenticationData().getBytes();
        } else
            statusBuilder.setHTTPStatus(401);
            return "Authentication required".getBytes();
    }
}