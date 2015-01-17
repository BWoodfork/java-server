package com.company.Handler;

import java.net.URLDecoder;

public class ParameterDecoder {
    public byte[] parseRequest(String request) {
        String[] splitRequest = request.split("\\?", 2);

        String stringWithoutrequestPath = splitRequest[1];

        int i = stringWithoutrequestPath.lastIndexOf("&");
        String[] splitAtLastAmpersand = {stringWithoutrequestPath.substring(0, i), stringWithoutrequestPath.substring(i)};
        String firstValue = splitAtLastAmpersand[0];
        String lastValue = splitAtLastAmpersand[1].substring(1);

        String URL = URLDecoder.decode(firstValue.replaceAll("\\=", " $0 ")) + URLDecoder.decode(lastValue.replaceAll("\\=", " $0 "));
        return URL.getBytes();
    }
}