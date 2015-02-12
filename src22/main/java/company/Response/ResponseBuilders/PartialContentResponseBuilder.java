package main.java.company.Response.ResponseBuilders;

import main.java.company.Handler.PartialContentHandler;
import main.java.company.Response.Headers.Options;
import main.java.company.Utilities.FileMatcher;
import main.java.company.request.Request;

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