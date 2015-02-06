package com.company.Response.ResponseGenerators;

import com.company.Handler.PostRequestHandler;
import com.company.Routes.IResponse;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostRequestIResponseGenerator implements IResponse {
    private PostRequestHandler postRequestHandler;
    private StatusBuilder statusBuilder;
    
    public PostRequestIResponseGenerator(StatusBuilder statusBuilder) {
        postRequestHandler = new PostRequestHandler();    
        this.statusBuilder = statusBuilder;
    }
    
    public boolean isAPostRequest(String method) {
        return method.equals("POST");
    }
    
    public boolean isAGetRequest(String method) {
        return method.equals("GET");
    }
    
    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }
    
    public byte[] getPostContentFile() throws Exception {
        return readFileBytesFromPath("../cob_spec/public/form");
    }

    public byte[] getBody(Request request) throws Exception {
        String method = request.getMethod();

        if (isAPostRequest(method)) {
            statusBuilder.setHTTPStatus(200);
            postRequestHandler.execute();
        } else if (isAGetRequest(method)) {
            statusBuilder.setHTTPStatus(200);
            return getPostContentFile();
        }
        
        return "The Requested Endpoint Is Not A Post Request".getBytes();
    }
}
