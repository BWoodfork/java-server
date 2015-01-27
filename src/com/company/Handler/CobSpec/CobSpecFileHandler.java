package com.company.Handler.CobSpec;

import com.company.Handler.ParameterDecoder;
import com.company.Handler.PartialContentHandler;
import com.company.Handler.PatchRequestHandler;
import com.company.Handler.PostRequestHandler;
import com.company.Response.BasicAuthenticationHandler;
import com.company.Response.FileRetriever;
import com.company.Routes;

public class CobSpecFileHandler {
    private BasicAuthenticationHandler basicAuthenticationHandler;
    private PatchRequestHandler patchRequestHandler;
    private PostRequestHandler postRequestHandler;
    private FileRetriever fileRetriever;
    
    public CobSpecFileHandler() {
        Routes routes = new Routes();
        fileRetriever = new FileRetriever(routes);
        basicAuthenticationHandler = new BasicAuthenticationHandler();
        patchRequestHandler = new PatchRequestHandler(fileRetriever);
        postRequestHandler = new PostRequestHandler(fileRetriever);
    }
    
    public byte[] getResponseForCobSpecTests(String method, String filePath, String data, String byteCount) throws Exception {
        if (filePath.equals(Routes.partialContentRoute())) {
            return PartialContentHandler.getPartialContents(fileRetriever.getPartialContentFile(),byteCount);
        } else if (filePath.startsWith(Routes.parametersRoute())) {
            return ParameterDecoder.parseRequest(filePath);
        } else if (filePath.equals(Routes.logsRoute())) {
            return basicAuthenticationHandler.getAuthenticationData(data);
        } else if (filePath.equals(Routes.patchContentRoute())) {
            return patchRequestHandler.parseRequest(method, filePath, data, byteCount);
        } else if (filePath.startsWith(Routes.parametersRoute())) {
            return ParameterDecoder.parseRequest(filePath);
        } else if (filePath.equals(Routes.formRoute())) {
            return postRequestHandler.parseRequest(method, filePath);
        } else if (filePath.equals(Routes.redirectRoute())) {
            return fileRetriever.redirect();
        }

        return Routes.notFoundRoute().getBytes();
    }
}
