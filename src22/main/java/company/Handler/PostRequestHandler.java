package main.java.company.Handler;

import java.nio.file.Files;
import java.nio.file.Path;

public class PostRequestHandler implements MethodHandler {
    private Path path;
    
    public PostRequestHandler(Path path) {
        this.path = path;
    }

    public void execute() {
        try {
            Files.write(path, "data=cosby".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}