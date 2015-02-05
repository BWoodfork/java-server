package com.company.Handler;

import com.company.Routes.RouteInterface;
import com.company.Utilities.FileMatcher;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BasicFileHandler implements RouteInterface {
    private StatusBuilder statusBuilder;
    private FileMatcher fileMatcher;
    
    public BasicFileHandler(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;
        fileMatcher = new FileMatcher();
    }
    
    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }
    
    public String getMatchedFileName(Request request) throws IOException {
        String filePath = request.getFilePath();
        String fileName = fileMatcher.matchRequestedFile(filePath);
        
        if (!filePath.equals(fileName)) {
            statusBuilder.setHTTPStatus(404);
            return fileName;
        } else
            statusBuilder.setHTTPStatus(200);
        return fileName;
    }

    public byte[] getBody(Request request) throws Exception {
        return readFileBytesFromPath("../cob_spec/public/" + getMatchedFileName(request));
    }
}