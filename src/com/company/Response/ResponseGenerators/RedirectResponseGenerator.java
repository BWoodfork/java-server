package com.company.Response.ResponseGenerators;

import com.company.Handler.RedirectRequestHandler;
import com.company.Routes.ResponseInterface;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

public class RedirectResponseGenerator implements ResponseInterface {
    private StatusBuilder statusBuilder;
    private RedirectRequestHandler redirectRequestHandler;
    
    public RedirectResponseGenerator(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;
        redirectRequestHandler = new RedirectRequestHandler();
        
    }

    public byte[] getBody(Request request) throws Exception {
        String method = request.getMethod();
        
        if (method.equals("GET")) {
            statusBuilder.setHTTPStatus(301);
            redirectRequestHandler.execute();
        }
        
        return .getBytes();
    }
}