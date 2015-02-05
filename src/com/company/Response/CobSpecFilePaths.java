package com.company.Response;

import com.company.Handler.CobSpec.UniqueRequestHandler;

public class CobSpecFilePaths {
    private UniqueRequestHandler uniqueRequestHandler;
    
    public CobSpecFilePaths(UniqueRequestHandler uniqueRequestHandler) {
        this.uniqueRequestHandler = uniqueRequestHandler;
    }

    public byte[] getFile(String method, String filePath, String data, String byteCount) throws Exception {
        return uniqueRequestHandler.getResponseForCobSpecTests(method, filePath, data, byteCount);
    }
}