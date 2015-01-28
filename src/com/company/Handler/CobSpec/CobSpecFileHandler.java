package com.company.Handler.CobSpec;

import com.company.Handler.ParameterDecoder;
import com.company.Handler.PartialContentHandler;
import com.company.Handler.PatchRequestHandler;
import com.company.Handler.PostRequestHandler;
import com.company.Handler.BasicAuthenticationHandler;
import com.company.Response.FilePaths;

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
    
// Implement the ability for these returned files to see whether the
// request requires them to be returned and pass the 
// filePath into the files instead of these strings?
    
    public static String partialFilePath = "/partial_content.txt";
    public static String parameterFilePath = "/parameters?";
    public static String logsFilePath = "/logs";
    public static String patchContentFilePath = "/patch-content.txt";
    public static String redirect = "/redirect";
    public static String formFilePath = "/form";

    public byte[] getResponseForCobSpecTests(String method, String filePath, String data, String byteCount) throws Exception {
        if (filePath.equals(partialFilePath)) {
            return PartialContentHandler.getPartialContents(filePaths.getPartialContentFile(),byteCount);
        } else if (filePath.startsWith(parameterFilePath)) {
            return ParameterDecoder.parseRequest(filePath);
        } else if (filePath.equals(logsFilePath)) {
            return basicAuthenticationHandler.getAuthenticationData(data);
        } else if (filePath.equals(patchContentFilePath)) {
            return patchRequestHandler.parseRequest(method, filePath, data);
        } else if (filePath.startsWith(parameterFilePath)) {
            return ParameterDecoder.parseRequest(filePath);
        } else if (filePath.equals(formFilePath)) {
            return postRequestHandler.parseRequest(method, filePath);
        } else if (filePath.equals(redirect)) {
            return filePaths.redirect();
        }

        return "Page Not Found".getBytes();
    }
}