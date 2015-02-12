package main.java.company.Handler;

import java.nio.file.Files;
import java.nio.file.Path;

public class PatchRequestHandler {
    private Path path;
    
    public PatchRequestHandler(Path path) {
        this.path = path;
    }

    public void writePatchedContent() throws Exception {
        Files.write(path, "patched content".getBytes());
    }
    
    public void writeDefaultContent() throws Exception {
        Files.write(path, "default content".getBytes());
    }
}