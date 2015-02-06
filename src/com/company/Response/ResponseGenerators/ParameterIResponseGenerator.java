package com.company.Response.ResponseGenerators;

import com.company.Handler.ParameterRequestHandler;
import com.company.Routes.IResponse;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.io.IOException;

public class ParameterIResponseGenerator implements IResponse {
    private ParameterRequestHandler parameterRequestHandler;
    private StatusBuilder statusBuilder;
    
    public ParameterIResponseGenerator(StatusBuilder statusBuilder) throws IOException {
        this.statusBuilder = statusBuilder;
        parameterRequestHandler = new ParameterRequestHandler();
    }
    
    public byte[] getBody(Request request) throws Exception {
        if (parameterRequestHandler.isAParameterRequest(request)) {
            statusBuilder.setHTTPStatus(200);
            return parameterRequestHandler.decodeRequest(request).getBytes();
        }
        
        return "The requested endpoint cannot be found".getBytes();
    }
}