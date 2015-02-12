package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.request.Request;

public class OptionsResponseBuilder extends ResponseBuilder {
    private StatusBuilder statusBuilder;
    
    public OptionsResponseBuilder(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;
    }
    
    public boolean isAnOptionsRequest(String method) {
        return method.equals("OPTIONS");
    }

    public byte[] buildResponse(Request request, Options options) throws Exception {
        statusBuilder.setHTTPStatus(200);
        options.setOptions("GET,HEAD,POST,OPTIONS,PUT");
        
        return "The Requested Endpoint Is Not A PATCH Request".getBytes();
    }
}