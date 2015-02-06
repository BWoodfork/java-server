package com.company.Response.ResponseGenerators;

import com.company.Handler.PartialContentHandler;
import com.company.Routes.IResponse;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PartialContentResponseGenerator implements IResponse {
    private StatusBuilder statusBuilder;

    public PartialContentResponseGenerator(StatusBuilder statusBuilder) {
        this.statusBuilder = statusBuilder;
    }
    
    public Path getPartialContentFilePath() throws Exception {
        return Paths.get("../cob_spec/public/partial_content.txt");
    }
    
    public boolean isAPartialRequest(Request request) throws IOException {
        String byteCount = request.getByteCount();
        return byteCount.startsWith("bytes=");
    }
    
    public byte[] getBody(Request request) throws Exception {
        if (isAPartialRequest(request)) {
            statusBuilder.setHTTPStatus(206);
            return PartialContentHandler.getPartialContents(Files.readAllBytes(getPartialContentFilePath()), request);
        }

        return "The requested endpoint does not exist".getBytes();
    }
}