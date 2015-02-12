package main.java.company.Response.ResponseBuilders;

import main.java.company.Handler.BasicAuthenticationHandler;
import main.java.company.Response.Headers.Options;
import main.java.company.request.Request;

import java.io.IOException;
import java.nio.file.Files;

public class BasicAuthenticationResponseBuilder extends ResponseBuilder {
    private BasicAuthenticationHandler basicAuthenticationHandler;
    private StatusBuilder statusBuilder;
    
    public BasicAuthenticationResponseBuilder(StatusBuilder statusBuilder) {
        basicAuthenticationHandler = new BasicAuthenticationHandler();
        this.statusBuilder = statusBuilder;
    }
    
    public boolean hasCredentials(String data) throws IOException {
        return data.equals(basicAuthenticationHandler.encodeAuthenticationCredentials() + "Connection:");
    }
    
    public byte[] buildResponse(Request request, Options options) throws Exception {
        String data = request.getData();
        
        if (hasCredentials(data)) {
            statusBuilder.setHTTPStatus(200);
            options.setOptions("GET,PUT,HEAD");
            return Files.readAllBytes(basicAuthenticationHandler.logFilePath());
        } else 
            statusBuilder.setHTTPStatus(401);
            options.setOptions("GET,PUT,HEAD");
            basicAuthenticationHandler.logRequests();
            return "Authentication required".getBytes();
    }
}