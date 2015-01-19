package com.company.Handler;

import com.company.Reponse.FileRetriever;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostRequestHandler {
    FileRetriever fileRetriever = new FileRetriever();

    public byte[] parseRequest(String method, String filePath) throws IOException {
        Path cosbyPath = Paths.get("/Users/8thlight/projects/cob_spec/public/cosby-data.txt");

        if (method.equals("POST") && filePath.equals(Routes.formRoute())) {
            Files.write(cosbyPath, "data=cosby".getBytes());
        } else if (method.equals("GET") && filePath.equals(Routes.formRoute())) {
            return fileRetriever.cosbyData();
        } else if (method.equals("PUT") && filePath.equals(Routes.formRoute())) {
            Files.write(cosbyPath, "data=heathcliff".getBytes());
        } else if (method.equals("GET") && filePath.equals(Routes.formRoute())) {
            return fileRetriever.cosbyData();
        } else if (method.equals("DELETE") && filePath.equals(Routes.formRoute())) {
            Files.write(cosbyPath, "Content Removed".getBytes());
            return fileRetriever.cosbyData();
        }
            return "Invalid Post Request".getBytes();
    }
}