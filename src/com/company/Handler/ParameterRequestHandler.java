package com.company.Handler;

import com.company.request.Request;

import java.io.IOException;
import java.net.URLDecoder;

public class ParameterRequestHandler {
    private String filePath;
    
    public String[] splitRequest(Request request) throws IOException {
        filePath = request.getFilePath();
        return filePath.split("\\?", 2);
    }
    
    public boolean isAParameterRequest(Request request) throws IOException {
       return splitRequest(request)[0].equals("/parameters");
    }
    
    public String parseRequest(Request request) throws IOException {
        filePath = request.getFilePath();
        String[] splitRequest = filePath.split("\\?", 2);

        return splitRequest[1];
    }
    
    public String decodeRequest(Request request) throws IOException {
        int lastInt = parseRequest(request).lastIndexOf("&");
        String parsedRequest = parseRequest(request);

        String[] splitAtLastAmpersand = {parsedRequest.substring(0, lastInt), parsedRequest.substring(lastInt)};
        String frontOfRequest = splitAtLastAmpersand[0];
        String endOfRequest = splitAtLastAmpersand[1].substring(1);

        return URLDecoder.decode(frontOfRequest.replaceAll("\\=", " $0 ")) + URLDecoder.decode(endOfRequest.replaceAll("\\=", " $0 "));
    }
}