package com.company.Response.ResponseGenerators;

import com.company.Handler.PatchRequestHandler;
import com.company.Routes.IResponse;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.io.IOException;

public class PatchContentResponseGenerator implements IResponse {
    private StatusBuilder statusBuilder;
    private PatchRequestHandler patchRequestHandler;
    
    public static String firstEtag = "60bb224c68b1ed765a0f84d910de58d0beea91c4";
    public static String secondEtag = "69bc18dc1edc9e1316348b2eaaca9df83898249f";
    
    public PatchContentResponseGenerator(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;
        patchRequestHandler = new PatchRequestHandler();
    }
    
    public boolean isAPatchRequest(Request request) throws IOException {
        String method = request.getMethod();
        return method.equals("PATCH");
    }
    
    public String getEtag(Request request) throws IOException {
        String data = request.getData();

        String[] strings = data.split("Connection:");
        return strings[0];
    }

    public byte[] getBody(Request request) throws Exception {
        if (getEtag(request).equals(firstEtag)) {
            statusBuilder.setHTTPStatus(204);
            patchRequestHandler.writePatchedContent();
        } else if (getEtag(request).equals(secondEtag)) {
            statusBuilder.setHTTPStatus(204);
            patchRequestHandler.writeDefaultContent();
        }
        
        return "The endpoint that you requested cannot be found".getBytes();
    }
}