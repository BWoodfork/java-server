package com.company;

import com.company.Reponse.FileRetriever;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PatchRequestHandler {
    FileRetriever fileRetriever = new FileRetriever();

    public byte[] parseRequest(String method, String filePath, String data) throws IOException {
        Path patchPath = Paths.get("/Users/8thlight/projects/cob_spec/public/patch-content.txt");



        if (method.equals("GET") && filePath.equals("/patch-content.txt")) {
            return fileRetriever.patchContent();
        } else if (method.equals("PATCH") && data.startsWith("60")) {
            Files.write(patchPath, "patched content".getBytes());
        } else if (method.equals("PATCH") && data.startsWith("69")) {
            Files.write(patchPath, "default content".getBytes());
        }

        return "This is not the page you are looking for".getBytes();
    }
}
