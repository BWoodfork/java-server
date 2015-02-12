package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Handler.ParameterRequestHandler;
import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.request.Request;

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