package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Handler.PartialContentHandler;
import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.Utilities.FileMatcher;
import main.com.mycompany.request.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PartialContentResponseBuilder extends ResponseBuilder {
    private StatusBuilder statusBuilder;
    private FileMatcher fileMatcher;

    public PartialContentResponseBuilder(StatusBuilder statusBuilder, FileMatcher fileMatcher) {
        this.statusBuilder = statusBuilder;
        this.fileMatcher = fileMatcher;
    }
    
    public Path getPartialContentFilePath(Request request) throws Exception {
        String filePath = request.getFilePath();
        
        return Paths.get("../cob_spec/public" + fileMatcher.matchRequestedFile(filePath));
    }
    
    public boolean isAPartialRequest(Request request) throws IOException {
        String byteCount = request.getByteCount();
        return byteCount.startsWith("bytes=");
    }
    
    public byte[] buildResponse(Request request, Options options) throws Exception {
        if (isAPartialRequest(request)) {
            statusBuilder.setHTTPStatus(206);
            options.setOptions("GET");
            return PartialContentHandler.getPartialContents(Files.readAllBytes(getPartialContentFilePath(request)), request);
        }
        
        return "The Requested Endpoint Is Not A PATCH Request".getBytes();
    }
}