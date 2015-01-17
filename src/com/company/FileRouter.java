package com.company;

import com.company.Handler.*;
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

        routes.put(Routes.rootRoute(), file.getHTMLPage());
        routes.put(Routes.file1Route(), file.getFile());
        routes.put(Routes.jpegRoute(), file.getJPEG());
        routes.put(Routes.pngRoute(), file.getPNG());
        routes.put(Routes.gifRoute(), file.getGIF());
        routes.put(Routes.file2Route(), file.getFile2());
        routes.put(Routes.redirectRoute(), file.refresh());
        routes.put(Routes.logsRoute(), basicAuthenticationHandler.getAuthenticationData(data));
        routes.put(Routes.patchContentRoute(), patchRequestHandler.parseRequest(method, filePath, data));
        routes.put(Routes.formRoute(), postRequestHandler.parseRequest(method, filePath));

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