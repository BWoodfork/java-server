package com.company.Handler;

import com.company.Response.CommandInterface;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PostRequestHandler implements CommandInterface {

    public Path getPostContentPath() throws Exception {
        return Paths.get("../cob_spec/public/cosby-data.txt");
    }
    
    public void execute() {
        try {
            Files.write(getPostContentPath(), "data=cosby".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

//            Files.write(getPostContentPath(), "data=cosby".getBytes());
//        } else if (method.equals("GET")) {
//            getPostContentFile();
//        } else if (method.equals("PUT")) {
//            Files.write(getPostContentPath(), "data=heathcliff".getBytes());
//        } else
//            deleteHandler.deleteFileContents(method, getPostContentPath());

//        return "Invalid Post Request".getBytes();
    }
}