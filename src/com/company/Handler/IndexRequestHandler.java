package com.company.Handler;

import com.company.Response.CommandInterface;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexRequestHandler implements CommandInterface {
    
    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }
    
//  Have execute use filematcher to generate the names of the files and then return
//    them to be used in html format?
    
    public void execute() {
        try {
            readFileBytesFromPath("../cob_spec/public/index.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}