package com.company.Response.ResponseGenerators;

import com.company.Handler.PostRequestHandler;
import com.company.Routes.RouteInterface;
import com.company.request.Request;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostRequestResponseGenerator implements RouteInterface {
    private PostRequestHandler postRequestHandler;
    
    public PostRequestResponseGenerator() {
        postRequestHandler = new PostRequestHandler();    
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
        return readFileBytesFromPath("../cob_spec/public/cosby-data.txt");
    }

    public byte[] getBody(Request request) throws Exception {
        String method = request.getMethod();

        if (isAPostRequest(method))
            postRequestHandler.execute();
        else if (isAGetRequest(method)) {
            return getPostContentFile();
        }
        
        return "The Requested Endpoint Is Not A Post Request".getBytes();
    }
}
