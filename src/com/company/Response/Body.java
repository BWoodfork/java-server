package com.company.Response;

import com.company.Handler.CobSpec.CobSpecFileHandler;
import com.company.Utilities.FileMatcher;

public class Body {
    private FilePaths filePaths;
    private CobSpecFileHandler cobSpecFileHandler;
    private CobSpecFilePaths cobSpecFilePaths;

    public Body() {
        filePaths = new FilePaths();
        CobSpecFileHandler cobSpecFileHandler = new CobSpecFileHandler();
        cobSpecFilePaths = new CobSpecFilePaths(cobSpecFileHandler);
    }
    
    public byte[] getBody(String method, String filePath, String data, String byteCount) throws Exception {
        if (filePath.equals("/")) {
            return filePaths.getIndex();
        } else if (filePath.equals(FileMatcher.matchRequestedFile(filePath))) {
            return filePaths.getFile(filePath);
        } else if (!filePath.equals(FileMatcher.matchRequestedFile(filePath))) {
            return cobSpecFilePaths.getFile(method, filePath, data, byteCount);
        } else return "Page cannnot be found".getBytes();
    }
}