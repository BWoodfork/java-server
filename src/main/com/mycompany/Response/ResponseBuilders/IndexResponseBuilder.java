package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.request.Request;
import main.com.mycompany.Handler.IndexRequestHandler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexResponseBuilder extends ResponseBuilder {
    private StatusBuilder statusBuilder;
    private IndexRequestHandler indexRequestHandler;
    
    public IndexResponseBuilder(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;
        indexRequestHandler = new IndexRequestHandler();
    }
    
    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }
    
    public byte[] buildResponse(Request request, Options options) throws Exception {
        String method = request.getMethod();
        
        if (method.equals("GET")) {
            statusBuilder.setHTTPStatus(200);
            options.setOptions("GET");
            indexRequestHandler.execute();
            
            return readFileBytesFromPath("../cob_spec/public/index.html");
        }

        return "The method you have requested is not valid".getBytes();
    }
}