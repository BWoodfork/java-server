package com.company;

import java.io.IOException;

public class FileRouter {
    private FileResponse file;
    private BasicAuthentication basicAuthentication;

    public FileRouter() {
        file = new FileResponse();
        basicAuthentication = new BasicAuthentication();
    }

    public byte[] routeFiles(String fullrequest) throws IOException {
        String unknown = "This is not the page you are looking for";
        String[] splitRequest = fullrequest.split(" ");

        if (splitRequest[1].equals("/file1")) {
            return file.getFile();
        }
        else if (splitRequest[1].equals("/")) {
            return file.getHTMLPage();
        } else if (splitRequest[1].equals("/image.jpeg")) {
            return file.getJPEG();
        } else if (splitRequest[1].equals("/image.png")) {
            return file.getPNG();
        } else if (splitRequest[1].equals("/image.gif")) {
            return file.getGIF();
        } else if (splitRequest[1].equals("/file2")) {
            return file.getFile2();
        } else if (splitRequest[1].equals("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff")) {
            return file.getDecoded();
        } else if (splitRequest[1].equals("/redirect")) {
            return file.refresh();
        } else if (splitRequest[1].equals("/logs")) {
            return basicAuthentication.authenticate(splitRequest[4]);
        }
        return unknown.getBytes();
    }
}