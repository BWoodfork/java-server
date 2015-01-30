package com.company.Handler;

import java.net.URLDecoder;

public class ParameterDecoder {
    
    public String[] splitRequest(String request) {
        return request.split("\\?", 2);
    }
    
    public String parseRequest(String request) {
        String[] splitRequest = request.split("\\?", 2);

        return splitRequest[1];
    }
    
    public String decodeRequest(String request) {
        int lastInt = parseRequest(request).lastIndexOf("&");
        String parsedRequest = parseRequest(request);

        String[] splitAtLastAmpersand = {parsedRequest.substring(0, lastInt), parsedRequest.substring(lastInt)};
        String frontOfRequest = splitAtLastAmpersand[0];
        String endOfRequest = splitAtLastAmpersand[1].substring(1);

        return URLDecoder.decode(frontOfRequest.replaceAll("\\=", " $0 ")) + URLDecoder.decode(endOfRequest.replaceAll("\\=", " $0 "));
    }

    public boolean isAParameterRequest(String request) {
       return splitRequest(request)[0].equals("/parameters");
    }
}