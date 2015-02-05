package com.company.Handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteHandler {
    public boolean isADeleteRequest(String method) {
        return method.equals("DELETE");
    }
    
    public Path deleteFileContents(String method, Path path) throws IOException {
        String emptyString = "";
        
        if (isADeleteRequest(method)) {
            Files.write(path, emptyString.getBytes());
            return path;
        }
        return path;
    }
}