package com.company.Handler;

import com.company.Response.CommandInterface;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PutRequestHandler implements CommandInterface {
    
    public Path getPutContentPath() throws Exception {
        return Paths.get("../cob_spec/public/cosby-data.txt");
    }
    
    public void execute() {
        try {
            Files.write(getPutContentPath(), "data=heathcliff".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
