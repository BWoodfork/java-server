package com.company;

import com.company.Handler.ParameterDecoder;
import com.company.Handler.PartialContentHandler;
import com.company.Handler.PatchRequestHandler;
import com.company.Handler.PostRequestHandler;
import com.company.Reponse.BasicAuthenticationHandler;
import com.company.Reponse.FileRetriever;

import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
public class FileRouter {
    private FileRetriever file;
    private BasicAuthenticationHandler basicAuthenticationHandler;
    private PostRequestHandler postRequestHandler;
    private PatchRequestHandler patchRequestHandler;
    private ParameterDecoder parameterDecoder;

    public FileRouter() {
        file = new FileRetriever();
        basicAuthenticationHandler = new BasicAuthenticationHandler();
        postRequestHandler = new PostRequestHandler();
        patchRequestHandler = new PatchRequestHandler();
        parameterDecoder = new ParameterDecoder();
    }

    public byte[] routeFiles(String method, String filePath, String data, String byteCount) throws IOException {
        Map<String, byte[]> routes = new HashMap<String, byte[]>();

        routes.put("/file1", file.getFile());
        routes.put("/", file.getHTMLPage());
        routes.put("/image.jpeg", file.getJPEG());
        routes.put("/image.png", file.getPNG());
        routes.put("/image.gif", file.getGIF());
        routes.put("/file2", file.getFile2());
        routes.put("/redirect", file.refresh());
        routes.put("/logs", basicAuthenticationHandler.getAuthenticationData(data));
        routes.put("/patch-content.txt", patchRequestHandler.parseRequest(method, filePath, data));
        routes.put("/form", postRequestHandler.parseRequest(method, filePath));

        for (Map.Entry<String, byte[]> route : routes.entrySet()) {
            if (filePath.equals(route.getKey())) {
                return route.getValue();
            } else if (filePath.equals("/partial_content.txt")) {
                return PartialContentHandler.getPartialContents(file.getFirstPartialContent(), byteCount);
            } else if (filePath.startsWith("/parameters?")) {
                return parameterDecoder.parseRequest(filePath);
            }
        }

        return "whatever".getBytes();
    }
}