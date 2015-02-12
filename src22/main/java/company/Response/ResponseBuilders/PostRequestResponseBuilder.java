package main.java.company.Response.ResponseBuilders;

import main.java.company.Handler.PostRequestHandler;
import main.java.company.Response.Headers.Options;
import main.java.company.request.Request;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PostRequestResponseBuilder extends ResponseBuilder {
    private StatusBuilder statusBuilder;
    
    public PostRequestResponseBuilder(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;
    }
    
    public boolean isAPostRequest(String method) {
        return method.equals("POST");
    }
    
    public Path getPostContentFile() throws Exception {
        return Paths.get("../cob_spec/public/form");
    }

    public byte[] buildResponse(Request request, Options options) throws Exception {
        PostRequestHandler postRequestHandler = new PostRequestHandler(getPostContentFile());
        
        if (isAPostRequest(request.getMethod())) {
            statusBuilder.setHTTPStatus(200);
            options.setOptions("POST");
            postRequestHandler.execute();
        }

        return "The Requested Endpoint Is Not A POST Request".getBytes();
    }
}