package com.company.Response.Headers;

import com.company.FileRouter;

import java.io.IOException;

public class BodyContents {
    public byte[] getBody(String method, String filePath, String data, String byteCount) throws IOException {
        FileRouter fileRouter = new FileRouter();

        return fileRouter.routeFiles(method, filePath, data, byteCount);
    }
}