package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.request.Request;

public class RedirectResponseBuilder extends ResponseBuilder {
    private StatusBuilder statusBuilder;

    public RedirectResponseBuilder(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;

    }

    public byte[] buildResponse(Request request, Options options) throws Exception {
        String method = request.getMethod();
        
        if (method.equals("GET")) {
            statusBuilder.setHTTPStatus(301);
            options.setOptions("GET");
            return "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>".getBytes();
        }
        
        return "The requested endpoint does not exist".getBytes();
    }
}