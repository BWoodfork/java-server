package com.company.Handler;

import com.company.Routes.RouteInterface;
import com.company.request.Request;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostRequestHandler implements RouteInterface {
    public boolean isAPostRequest(String method) {
       return method.equals("POST");
    }
    
    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }
    
    public byte[] getPostContentFile() throws Exception {
        return readFileBytesFromPath("../cob_spec/public/cosby-data.txt");
    }
    
    public Path getPostContentPath() throws Exception {
        return Paths.get("../cob_spec/public/cosby-data.txt");
    }

    public byte[] getBody(Request request) throws Exception {
//        statusBuilder.setHTTPStatus(200);
        
        String method = request.getMethod();
        
        DeleteHandler deleteHandler = new DeleteHandler();
        
        if (isAPostRequest(method)) {
            Files.write(getPostContentPath(), "data=cosby".getBytes());
        } else if (method.equals("GET")) {
            return getPostContentFile();
        } else if (method.equals("PUT")) {
            Files.write(getPostContentPath(), "data=heathcliff".getBytes());
        } else
            deleteHandler.deleteFileContents(method, getPostContentPath());

        return "Invalid Post Request".getBytes();
    }


}