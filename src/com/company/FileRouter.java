package com.company;

import com.company.Reponse.FileRetriever;

import java.io.IOException;
import java.net.URLDecoder;

public class FileRouter {
    private FileRetriever file;
    private BasicAuthenticationHandler basicAuthenticationHandler;
    private PostRequestHandler postRequestHandler;
    private PatchRequestHandler patchRequestHandler;
    private PartialContentHandler partialContentHandler;

    public FileRouter() {
        file = new FileRetriever();
        basicAuthenticationHandler = new BasicAuthenticationHandler();
        postRequestHandler = new PostRequestHandler();
        patchRequestHandler = new PatchRequestHandler();
        partialContentHandler = new PartialContentHandler();
    }

    public byte[] routeFiles(String method, String filePath, String data, String byteCount) throws IOException {
        String unknown = "This is not the page you are looking for";

        System.out.println(URLDecoder.decode("variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff"));

        if (filePath.equals("/file1")) {
            return file.getFile();
        } else if (filePath.equals("/")) {
            return file.getHTMLPage();
        } else if (filePath.equals("/image.jpeg")) {
            return file.getJPEG();
        } else if (filePath.equals("/image.png")) {
            return file.getPNG();
        } else if (filePath.equals("/image.gif")) {
            return file.getGIF();
        } else if (filePath.equals("/file2")) {
            return file.getFile2();
        } else if (filePath.equals("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff")) {
            return file.getDecoded();
        } else if (filePath.equals("/redirect")) {
            return file.refresh();
        } else if (filePath.equals("/logs")) {
            return basicAuthenticationHandler.getAuthenticationData(data);
        } else if (filePath.equals("/form")) {
            return postRequestHandler.parseRequest(method, filePath);
        } else if (filePath.equals("/patch-content.txt")) {
            return patchRequestHandler.parseRequest(method, filePath, data);
        } else if (filePath.equals("/partial_content.txt")) {
            return partialContentHandler.getPartialContents(file.getFirstPartialContent(), byteCount);
        }
        return unknown.getBytes();
    }
}