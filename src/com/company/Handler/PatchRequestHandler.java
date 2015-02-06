package com.company.Handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PatchRequestHandler {

    public Path getPatchFilePath() throws IOException {
        Path path = Paths.get("../cob_spec/public/patch-content.txt");
        return path.toAbsolutePath();
    }

    public void writePatchedContent() throws Exception {
        Files.write(getPatchFilePath(), "patched content".getBytes());
    }
    
    public void writeDefaultContent() throws Exception {
        Files.write(getPatchFilePath(), "default content".getBytes());
    }
}