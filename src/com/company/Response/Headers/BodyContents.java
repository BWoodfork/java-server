package com.company.Response.Headers;

import com.company.Response.FileRetriever;
import com.company.Routes;

public class BodyContents {
    private FileRetriever fileRetriever;

    public BodyContents() {
        Routes routes = new Routes();
        fileRetriever = new FileRetriever(routes);
    }
    
    public byte[] getBody(String method, String filePath, String data, String byteCount) throws Exception {
        if (filePath.equals("/")) {
            return fileRetriever.getIndex();
        } else return fileRetriever.getFile(method, filePath, data, byteCount);
    }
}