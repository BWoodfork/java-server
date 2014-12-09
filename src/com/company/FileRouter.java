package com.company;

import com.company.HeaderData.HeaderOutput;

import java.io.IOException;

public class FileRouter {
    private FileResponse file;

    public FileRouter() {
        file = new FileResponse();
    }

    public byte[] routeFiles(String theFilePath) throws IOException {
        String unknown = "This is not the page you are looking for";

        if (theFilePath.equals("/file1")) {
            return file.getFile();
        }
        else if (theFilePath.equals("/")) {
            return file.getHTMLPage();
        } else if (theFilePath.equals("/image.jpeg")) {
            return file.getJPEG();
        } else if (theFilePath.equals("/image.png")) {
            return file.getPNG();
        } else if (theFilePath.equals("/image.gif")) {
            return file.getGIF();
        } else if (theFilePath.equals("/file2")) {
            return file.getFile2();
//        } else if (theFilePath.equals("/logs")) {
//            return file.getAuthenticationRequired();
        } else if (theFilePath.equals("/logs")) {
            return file.getFileLogger();
        } else if (theFilePath.equals("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff")) {
            return file.getDecoded();
        } else if (theFilePath.equals("/partial_content.txt")) {
            return file.getPartialContent();
        }
        return unknown.getBytes();
    }
}