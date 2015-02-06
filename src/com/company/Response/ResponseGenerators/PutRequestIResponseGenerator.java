package com.company.Response.ResponseGenerators;

import com.company.Handler.PutRequestHandler;
import com.company.Routes.IResponse;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PutRequestIResponseGenerator implements IResponse {
    private PutRequestHandler putRequestHandler;
    private StatusBuilder statusBuilder;
    
    public PutRequestIResponseGenerator(StatusBuilder statusBuilder) {
        putRequestHandler = new PutRequestHandler();
        this.statusBuilder = statusBuilder;
    }
    
    public boolean isAPutRequest(String method) {
        return method.equals("PUT");
    }

    public boolean isAGetRequest(String method) {
        return method.equals("GET");
    }

    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] getPutContentFile() throws Exception {
        return readFileBytesFromPath("../cob_spec/public/cosby-data.txt");
    }

    public byte[] getBody(Request request) throws Exception {
        String method = request.getMethod();

        if (isAPutRequest(method)) {
            statusBuilder.setHTTPStatus(200);
            putRequestHandler.execute();
        } else if (isAGetRequest(method)) {
            statusBuilder.setHTTPStatus(200);
            return getPutContentFile();
        }

        return "The Requested Endpoint Is Not A PUT Request".getBytes();
    }
}
