package com.company.Handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PatchRequestHandler {

    public PatchRequestHandler() {
    }
    
    public boolean isAPatchRequest(String method) {
       return method.equals("PATCH");
    }

    public Path getPatchFilePath() throws IOException {
        Path path = Paths.get("../cob_spec/extension-files/patch-content.txt");
        return path.toAbsolutePath();
    }
    
    public byte[] parseRequest(String method, String filePath, String data) throws Exception {
        String[] strings = data.split("Connection:");
        String eTagHash = strings[0];

        String firstPatchString = "60bb224c68b1ed765a0f84d910de58d0beea91c4";
        String secondPatchString = "69bc18dc1edc9e1316348b2eaaca9df83898249f";

        if (method.equals("GET") && filePath.equals("/patch-content.txt")) {
            return Files.readAllBytes(getPatchFilePath());
        } else if (isAPatchRequest(method) && eTagHash.equals(firstPatchString)) {
            Files.write(getPatchFilePath(), "patched content".getBytes());
        } else if (isAPatchRequest(method) && eTagHash.equals(secondPatchString)) {
            Files.write(getPatchFilePath(), "default content".getBytes());
        } return "This is not the page you are looking for".getBytes();

    }
}