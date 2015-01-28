package com.company.Response;

public class Body {
    private FilePaths filePaths;

    public Body() {
        filePaths = new FilePaths();
    }
    
    public byte[] getBody(String method, String filePath, String data, String byteCount) throws Exception {
        if (filePath.equals("/")) {
            return filePaths.getIndex();
        } else return filePaths.getFile(method, filePath, data, byteCount);
    }
}