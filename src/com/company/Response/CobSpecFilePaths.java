package com.company.Response;

import com.company.Handler.CobSpec.CobSpecFileHandler;

public class CobSpecFilePaths {
    private CobSpecFileHandler cobSpecFileHandler;
    
    public CobSpecFilePaths(CobSpecFileHandler cobSpecFileHandler) {
        this.cobSpecFileHandler = cobSpecFileHandler;
    }

    public byte[] getFile(String method, String filePath, String data, String byteCount) throws Exception {
        return cobSpecFileHandler.getResponseForCobSpecTests(method, filePath, data, byteCount);
    }
}