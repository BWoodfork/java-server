package com.company.Handler;

import com.company.Response.FileRetriever;
import com.company.Routes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostRequestHandler {
    private FileRetriever fileRetriever;
    
    public PostRequestHandler(FileRetriever fileRetriever) {
      this.fileRetriever = fileRetriever;
    }
    
    public Path getFilePath() {
        return Paths.get("../cob_spec/public/cosby-data.txt");
    }

    public byte[] parseRequest(String method, String filePath) throws Exception {
        if (method.equals("POST") && filePath.equals(Routes.formRoute())) {
            Files.write(getFilePath(), "data=cosby".getBytes());
        } else if (method.equals("GET") && filePath.equals(Routes.formRoute())) {
            return fileRetriever.getPostDataFile();
        } else if (method.equals("PUT") && filePath.equals(Routes.formRoute())) {
            Files.write(getFilePath(), "data=heathcliff".getBytes());
        } else if (method.equals("GET") && filePath.equals(Routes.formRoute())) {
            return fileRetriever.getPostDataFile();
        } else if (method.equals("DELETE") && filePath.equals(Routes.formRoute())) {
            Files.write(getFilePath(), "Content Removed".getBytes());
            return fileRetriever.getPostDataFile();
        }
        
        return "Invalid Post Request".getBytes();
    }
}