package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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

        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/cosby-data.txt");

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
        } else if (splitRequest[0].equals("POST") && splitRequest[1].equals("/form")) {
            Files.write(path, "data=cosby".getBytes());
        } else if (splitRequest[0].equals("GET") && splitRequest[1].equals("/form")) {
            return file.cosbyData();
        } else if (splitRequest[0].equals("PUT") && splitRequest[1].equals("/form")) {
            Files.write(path, "data=heathcliff".getBytes());
        } else if (splitRequest[0].equals("GET") && splitRequest[1].equals("/form")) {
            return file.cosbyData();
        } else if (splitRequest[0].equals("DELETE") && splitRequest[1].equals("/form")) {
            Files.write(path, "These are not the droids you're looking for".getBytes());
            return file.cosbyData();
        } else if (splitRequest[3].startsWith("bytes=0-4")) {
            return "This ".getBytes();
        } else if (splitRequest[3].startsWith("bytes=-6")) {
//            System.out.println(new String(Arrays.copyOfRange(file.getFirstPartialContent(), 71, 77)));
            return " 206.".getBytes();
        }
        return unknown.getBytes();
    }
}