package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Handler.DeleteHandler;
import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.request.Request;

import java.io.IOException;

public class DeleteRequestResponseBuilder extends ResponseBuilder {
    private DeleteHandler deleteHandler;
    private StatusBuilder statusBuilder;
    
    public DeleteRequestResponseBuilder(StatusBuilder statusBuilder) {
        deleteHandler = new DeleteHandler();
        this.statusBuilder = statusBuilder;
    }
    
    public boolean isADeleteRequest(String method) {
        return method.equals("DELETE");
    }
    
     public byte[] buildResponse(Request request, Options options) throws IOException {
         String method = request.getMethod();
         
         if (isADeleteRequest(method)) {
             statusBuilder.setHTTPStatus(200);
             options.setOptions("DELETE");
             deleteHandler.execute();
         }
         
        return "The Requested Endpoint Is Not A DELETE Request".getBytes();
    }
}