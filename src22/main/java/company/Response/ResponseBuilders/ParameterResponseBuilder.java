package main.java.company.Response.ResponseBuilders;

import main.java.company.Handler.ParameterRequestHandler;
import main.java.company.Response.Headers.Options;
import main.java.company.request.Request;

import java.io.IOException;

public class ParameterResponseBuilder extends ResponseBuilder {
    private ParameterRequestHandler parameterRequestHandler;
    private StatusBuilder statusBuilder;
    
    public ParameterResponseBuilder(StatusBuilder statusBuilder) throws IOException {
        this.statusBuilder = statusBuilder;
        parameterRequestHandler = new ParameterRequestHandler();
    }
    
    public byte[] buildResponse(Request request, Options options) throws Exception {
        if (parameterRequestHandler.isAParameterRequest(request)) {
            statusBuilder.setHTTPStatus(200);
            options.setOptions("GET");
            return parameterRequestHandler.decodeRequest(request).getBytes();
        }
        
        return "The Requested Endpoint Is Not A PATCH Request".getBytes();
    }
}