package com.company.Handler;

import com.company.Response.CommandInterface;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostRequestHandler implements CommandInterface {

    public Path getPostContentPath() throws Exception {
        return Paths.get("../cob_spec/public/form");
    }
    
    public void execute() {
        try {
            Files.write(getPostContentPath(), "data=cosby".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}