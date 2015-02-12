package main.java.company.Handler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PutRequestHandler implements MethodHandler {
    
    public Path getPutContentPath() throws Exception {
        return Paths.get("../cob_spec/public/form");
    }
    
    public void execute() {
        try {
            Files.write(getPutContentPath(), "data=heathcliff".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
