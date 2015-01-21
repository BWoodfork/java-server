package com.company.Handler;

import com.company.Reponse.FileRetriever;
import com.company.Routes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostRequestHandler {
    FileRetriever fileRetriever = new FileRetriever();
    
    public Path getFilePath() {
        return Paths.get("../cob_spec/public/cosby-data.txt");
    }

    public byte[] parseRequest(String method, String filePath) throws IOException {
        if (method.equals("POST") && filePath.equals(Routes.formRoute())) {
            Files.write(getFilePath(), "data=cosby".getBytes());
        } else if (method.equals("GET") && filePath.equals(Routes.formRoute())) {
            return fileRetriever.cosbyData();
        } else if (method.equals("PUT") && filePath.equals(Routes.formRoute())) {
            Files.write(getFilePath(), "data=heathcliff".getBytes());
        } else if (method.equals("GET") && filePath.equals(Routes.formRoute())) {
            return fileRetriever.cosbyData();
        } else if (method.equals("DELETE") && filePath.equals(Routes.formRoute())) {
            Files.write(getFilePath(), "Content Removed".getBytes());
            return fileRetriever.cosbyData();
        }
        
        return "Invalid Post Request".getBytes();
    }
}