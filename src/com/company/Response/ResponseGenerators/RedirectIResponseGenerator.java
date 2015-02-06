package com.company.Response.ResponseGenerators;

import com.company.Handler.RedirectRequestHandler;
import com.company.Routes.IResponse;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

public class RedirectIResponseGenerator implements IResponse {
    private StatusBuilder statusBuilder;
    private RedirectRequestHandler redirectRequestHandler;
    
    public RedirectIResponseGenerator(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;
        redirectRequestHandler = new RedirectRequestHandler();
        
    }

    public byte[] getBody(Request request) throws Exception {
        String method = request.getMethod();
        
        if (method.equals("GET")) {
            statusBuilder.setHTTPStatus(301);
            redirectRequestHandler.execute();
        }
        
        return "The requested endpoint does not exist".getBytes();
    }
}