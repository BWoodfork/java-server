package com.company.Response.ResponseGenerators;

import com.company.Handler.IndexRequestHandler;
import com.company.Routes.ResponseInterface;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexResponseGenerator implements ResponseInterface {
    private StatusBuilder statusBuilder;
    private IndexRequestHandler indexRequestHandler;
    
    public IndexResponseGenerator(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;
        indexRequestHandler = new IndexRequestHandler();
    }
    
    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }
    
    public byte[] getBody(Request request) throws Exception {
        String method = request.getMethod();
        
        if (method.equals("GET")) {
            statusBuilder.setHTTPStatus(200);
            indexRequestHandler.execute();
            
            return readFileBytesFromPath("../cob_spec/public/index.html");
        }
        
        return "The method you have requested is not valid".getBytes();
    }
}