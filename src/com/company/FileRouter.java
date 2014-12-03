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
        } else if (theFilePath.getFilePath().equals("/image.jpeg")) {
            return file.getJPEG();
        } else if (theFilePath.getFilePath().equals("/image.png")) {
            return file.getPNG();
        } else if (theFilePath.getFilePath().equals("/image.gif")) {
            return file.getGIF();
        } else if (theFilePath.getFilePath().equals("/file2")) {
            return file.getFile2();
        }
        return unknown.getBytes();
    }
}