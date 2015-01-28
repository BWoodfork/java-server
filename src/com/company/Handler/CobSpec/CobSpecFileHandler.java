package com.company.Handler.CobSpec;

import com.company.Handler.ParameterDecoder;
import com.company.Handler.PartialContentHandler;
import com.company.Handler.PatchRequestHandler;
import com.company.Handler.PostRequestHandler;
import com.company.Handler.BasicAuthenticationHandler;
import com.company.Response.FilePaths;
import com.company.Routes;

public class CobSpecFileHandler {
    private BasicAuthenticationHandler basicAuthenticationHandler;
    private PatchRequestHandler patchRequestHandler;
    private PostRequestHandler postRequestHandler;
    private FilePaths filePaths;
    
    public CobSpecFileHandler() {
        filePaths = new FilePaths();
        basicAuthenticationHandler = new BasicAuthenticationHandler();
        patchRequestHandler = new PatchRequestHandler(filePaths);
        postRequestHandler = new PostRequestHandler(filePaths);
    }
    
    public byte[] getResponseForCobSpecTests(String method, String filePath, String data, String byteCount) throws Exception {
        if (filePath.equals(Routes.partialContentRoute())) {
            return PartialContentHandler.getPartialContents(filePaths.getPartialContentFile(),byteCount);
        } else if (filePath.startsWith(Routes.parametersRoute())) {
            return ParameterDecoder.parseRequest(filePath);
        } else if (filePath.equals(Routes.logsRoute())) {
            return basicAuthenticationHandler.getAuthenticationData(data);
        } else if (filePath.equals(Routes.patchContentRoute())) {
            return patchRequestHandler.parseRequest(method, filePath, data);
        } else if (filePath.startsWith(Routes.parametersRoute())) {
            return ParameterDecoder.parseRequest(filePath);
        } else if (filePath.equals(Routes.formRoute())) {
            return postRequestHandler.parseRequest(method, filePath);
        } else if (filePath.equals(Routes.redirectRoute())) {
            return filePaths.redirect();
        }

        return Routes.notFoundRoute().getBytes();
    }
}