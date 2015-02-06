package com.company.Response.ResponseGenerators;

import com.company.Handler.DeleteHandler;
import com.company.Routes.IResponse;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.io.IOException;

public class DeleteRequestIResponseGenerator implements IResponse {
    private DeleteHandler deleteHandler;
    private StatusBuilder statusBuilder;
    
    public DeleteRequestIResponseGenerator(StatusBuilder statusBuilder) {
        deleteHandler = new DeleteHandler();
        this.statusBuilder = statusBuilder;
    }
    
    public boolean isADeleteRequest(String method) {
        return method.equals("DELETE");
    }
    
     public byte[] getBody(Request request) throws IOException {
        String method = request.getMethod();

        if (isADeleteRequest(method)) {
            statusBuilder.setHTTPStatus(200);
            deleteHandler.execute();
        }
        
        return "The Requested Endpoint Is Not A DELETE Request".getBytes();
    }
}
