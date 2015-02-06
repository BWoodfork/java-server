package com.company.Response.ResponseGenerators;

import com.company.Handler.ParameterRequestHandler;
import com.company.Routes.ResponseInterface;
import com.company.request.Request;

import java.io.IOException;

public class ParameterResponseGenerator implements ResponseInterface {
    private ParameterRequestHandler parameterRequestHandler;
    
    public ParameterResponseGenerator() throws IOException {
        parameterRequestHandler = new ParameterRequestHandler();
    }
    
    public byte[] getBody(Request request) throws Exception {
        if (parameterRequestHandler.isAParameterRequest(request))
            return parameterRequestHandler.decodeRequest(request).getBytes();
        
        return "The requested endpoint cannot be found".getBytes();
    }
}