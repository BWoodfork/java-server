package main.com.mycompany.Handler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexRequestHandler implements MethodHandler {
    
    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }
    
    public void execute() {
        try {
            readFileBytesFromPath("../cob_spec/public/index.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}