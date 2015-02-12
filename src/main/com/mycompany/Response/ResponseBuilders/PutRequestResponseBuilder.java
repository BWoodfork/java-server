package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Handler.PutRequestHandler;
import main.com.mycompany.request.Request;
import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.Utilities.FileMatcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PutRequestResponseBuilder extends ResponseBuilder {
    private PutRequestHandler putRequestHandler;
    private StatusBuilder statusBuilder;
    private FileMatcher fileMatcher;
    
    public PutRequestResponseBuilder(StatusBuilder statusBuilder, FileMatcher fileMatcher) {
        putRequestHandler = new PutRequestHandler();
        this.statusBuilder = statusBuilder;
        this.fileMatcher = fileMatcher;
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

    public byte[] getPutContentFile(Request request) throws Exception {
        String filePath = request.getFilePath();
        
        return readFileBytesFromPath("../cob_spec/public" + fileMatcher.matchRequestedFile(filePath));
    }

    public byte[] buildResponse(Request request, Options options) throws Exception {
        statusBuilder.setHTTPStatus(200);
        options.setOptions("PUT");
        putRequestHandler.execute();

        return "The Requested Endpoint Is Not A PUT Request".getBytes();
    }
}
