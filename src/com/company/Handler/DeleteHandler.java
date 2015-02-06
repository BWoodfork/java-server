package com.company.Handler;

import com.company.Response.CommandInterface;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteHandler implements CommandInterface {

    public Path getPostContentPath() throws Exception {
        return Paths.get("../cob_spec/public/form");
    }

    public void execute() {
        String emptyString = "";
        
        try {
            Files.write(getPostContentPath(), emptyString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}