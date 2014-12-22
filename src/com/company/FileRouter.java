package com.company;

import com.company.Handler.ParameterDecoder;
import com.company.Handler.PartialContentHandler;
import com.company.Handler.PatchRequestHandler;
import com.company.Handler.PostRequestHandler;
import com.company.Reponse.BasicAuthenticationHandler;
import com.company.Reponse.FileRetriever;

import java.io.IOException;

public class FileRouter {
    private FileRetriever file;
    private BasicAuthenticationHandler basicAuthenticationHandler;
    private PostRequestHandler postRequestHandler;
    private PatchRequestHandler patchRequestHandler;
    private PartialContentHandler partialContentHandler;
    private ParameterDecoder parameterDecoder;

    public FileRouter() {
        file = new FileRetriever();
        basicAuthenticationHandler = new BasicAuthenticationHandler();
        postRequestHandler = new PostRequestHandler();
        patchRequestHandler = new PatchRequestHandler();
        partialContentHandler = new PartialContentHandler();
        parameterDecoder = new ParameterDecoder();
    }

    public byte[] routeFiles(String method, String filePath, String data, String byteCount) throws IOException {
        String unknown = "This is not the page you are looking for";

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
        } else if (parameterDecoder.parseRequest().equals(filePath)) {
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

//    public byte[] routeFiles(String method, String filePath, String data, String byteCount) throws IOException {
//        String unknown = "These are not the droids you are looking for";
//
//        HashMap<byte[], String> paths = new HashMap<byte[], String>();
//
//        paths.put(file.getFile(), "/file1");
//        paths.put(file.getHTMLPage(), "/");
//        paths.put(file.getJPEG(), "/image.jpeg");
//        paths.put(file.getPNG(), "/image.png");
//        paths.put(file.getGIF(), "/image.gif");
//        paths.put(file.getFile2(), "/file2");
//        paths.put(file.refresh(), "/redirect");
//        paths.put(basicAuthenticationHandler.getAuthenticationData(data), "/logs");
//        paths.put(postRequestHandler.parseRequest(method, filePath), "/form");
//        paths.put(patchRequestHandler.parseRequest(method, filePath, data), "/patch-content.txt");
////        paths.put(partialContentHandler.getPartialContents(file.getFirstPartialContent(), byteCount), "/partial_content.txt");
//
//        Collection<String> values = paths.values();
////        Set<byte[]> keys = paths.keySet();
//
//        for (String value : values) {
//            if (value.equals(filePath)) {
//                paths.get()
//            }
//        }
//
//        return unknown.getBytes();
//    }
}