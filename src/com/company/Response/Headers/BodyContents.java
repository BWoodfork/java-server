package com.company.Response.Headers;

import com.company.FileRouter;

import java.io.IOException;

public class BodyContents {
    private FileRouter fileRouter;
    
    public BodyContents(FileRouter fileRouter) {
        this.fileRouter = fileRouter;
    }
    
    public byte[] getBody(String method, String filePath, String data, String byteCount) throws IOException {
        return fileRouter.routeFiles(method, filePath, data, byteCount);
    }
}