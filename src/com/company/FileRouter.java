package com.company;

import java.io.IOException;

public class FileRouter {
    private FileResponse file;

    public FileRouter() {
        file = new FileResponse();
    }

    public byte[] routeFiles(RequestParser theFilePath) throws IOException {
        String unknown = "This is not the page you are looking for";

        if (theFilePath.getFilePath().equals("/file1")) {
            return file.getFile();
        }
        else if (theFilePath.getFilePath().equals("/")) {
            return file.getHTMLPage();
        }
        return unknown.getBytes();
    }
}