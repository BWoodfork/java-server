package com.company.Handler;

import com.company.Response.FileRetriever;
import com.company.Routes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PatchRequestHandler {
    private FileRetriever fileRetriever;
    
    public PatchRequestHandler(FileRetriever fileRetriever) {
       this.fileRetriever = fileRetriever;
    }

    public Path getPatchFilePath() {
        Path path = Paths.get("../cob_spec/extension-files/patch-content.txt");
        return path.toAbsolutePath();
    }
    
    public byte[] parseRequest(String method, String filePath, String data, String byteCount) throws Exception {
        String[] strings = data.split("Connection:");
        String eTagHash = strings[0];

        String firstPatchString = "60bb224c68b1ed765a0f84d910de58d0beea91c4";
        String secondPatchString = "69bc18dc1edc9e1316348b2eaaca9df83898249f";
        
        if (method.equals("GET") && filePath.equals(Routes.patchContentRoute())) {
            return fileRetriever.getPatchContentFile();
        } else if (filePath.equals(Routes.patchContentRoute()) && eTagHash.equals(firstPatchString)) {
            Files.write(getPatchFilePath(), "patched content".getBytes());
        } else if (filePath.equals(Routes.patchContentRoute()) && eTagHash.equals(secondPatchString)) {
            Files.write(getPatchFilePath(), "default content".getBytes());
        } return "This is not the page you are looking for".getBytes();
    }
}