package main.java.company.Response.ResponseBuilders;

import main.java.company.Handler.PatchRequestHandler;
import main.java.company.Response.Headers.Options;
import main.java.company.Utilities.FileMatcher;
import main.java.company.request.Request;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PatchContentResponseBuilder extends ResponseBuilder {
    private StatusBuilder statusBuilder;
    private FileMatcher fileMatcher;

    public static String firstEtag = "60bb224c68b1ed765a0f84d910de58d0beea91c4";
    public static String secondEtag = "69bc18dc1edc9e1316348b2eaaca9df83898249f";
    
    public PatchContentResponseBuilder(StatusBuilder statusBuilder, FileMatcher fileMatcher) {
        this.statusBuilder = statusBuilder;
        this.fileMatcher = fileMatcher;
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
    
    public Path getPatchFilePath(Request request) throws IOException {
        String filePath = request.getFilePath();
        
        Path path = Paths.get("../cob_spec/public" + fileMatcher.matchRequestedFile(filePath));
        return path.toAbsolutePath();
    }

    public byte[] buildResponse(Request request, Options options) throws Exception {
        PatchRequestHandler patchRequestHandler = new PatchRequestHandler(getPatchFilePath(request));
        
        if (getEtag(request).equals(firstEtag)) {
            statusBuilder.setHTTPStatus(204);
            options.setOptions("PATCH");
            patchRequestHandler.writePatchedContent();
        } else if (getEtag(request).equals(secondEtag)) {
            options.setOptions("PATCH");
            statusBuilder.setHTTPStatus(204);
            patchRequestHandler.writeDefaultContent();
        }
        
        return "The Requested Endpoint Is Not A PATCH Request".getBytes();
    }
}