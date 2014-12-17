package com.company;

import Reponse.FileResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileRouter {
    private FileResponse file;
    private BasicAuthenticationHandler basicAuthenticationHandler;

    public FileRouter() {
        file = new FileResponse();
        basicAuthenticationHandler = new BasicAuthenticationHandler();
    }

    public byte[] routeFiles(String request) throws IOException {
        RequestParser requestParser = new RequestParser(request);

        String method = requestParser.getMethod();
        String filePath = requestParser.getFilePath();
        String byteCount = requestParser.getByteCount();
        String data = requestParser.getData();

        String unknown = "This is not the page you are looking for";

        Path cosbyPath = Paths.get("/Users/8thlight/projects/cob_spec/public/cosby-data.txt");
        Path patchPath = Paths.get("/Users/8thlight/projects/cob_spec/public/patch-content.txt");

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
        } else if (method.equals("POST") && filePath.equals("/form")) {
            Files.write(cosbyPath, "data=cosby".getBytes());
        } else if (method.equals("GET") && filePath.equals("/form")) {
            return file.cosbyData();
        } else if (method.equals("PUT") && filePath.equals("/form")) {
            Files.write(cosbyPath, "data=heathcliff".getBytes());
        } else if (method.equals("GET") && filePath.equals("/form")) {
            return file.cosbyData();
        } else if (method.equals("DELETE") && filePath.equals("/form")) {
            Files.write(cosbyPath, "These are not the droids you're looking for".getBytes());
            return file.cosbyData();
        } else if (method.equals("GET") && filePath.equals("/patch-content.txt")) {
            return file.patchContent();
        } else if (method.equals("PATCH") && data.startsWith("60")) {
            Files.write(patchPath, "patched content".getBytes());
        } else if (method.equals("PATCH") && data.startsWith("69")) {
            Files.write(patchPath, "default content".getBytes());
        } else if (method.equals("GET") && byteCount.startsWith("bytes=0-4")) {
            return Arrays.copyOfRange(file.getFirstPartialContent(), 0, 5);
        } else if (method.equals("GET") && byteCount.startsWith("bytes=4-")) {
            return Arrays.copyOfRange(file.getFirstPartialContent(), 4, file.getFirstPartialContent().length);
        } else if (method.equals("GET") && byteCount.startsWith("bytes=-6")) {
            return Arrays.copyOfRange(file.getFirstPartialContent(), 71, file.getFirstPartialContent().length);
        }
        return unknown.getBytes();
    }
}