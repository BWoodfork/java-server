package main.java.company.Handler;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BasicAuthenticationHandler {
    
    public String encodeAuthenticationCredentials() throws IOException {
        String username = "admin";
        String password = "hunter2";

        byte[] encodedPassword = (username + ":" + password).getBytes();
        BASE64Encoder encoder = new BASE64Encoder();
        
        return encoder.encode(encodedPassword);
    }
    
    public Path logFilePath() {
        return Paths.get("../cob_spec/public/logs");
    }
    
    public void logRequests() throws Exception {
        File file = new File("../cob_spec/public/logs");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);
    }
}