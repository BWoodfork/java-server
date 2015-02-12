package main.java.company.Response.ResponseBuilders;

import main.java.company.Response.Headers.Options;
import main.java.company.Utilities.FileMatcher;
import main.java.company.request.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class BasicResponseGenerator extends ResponseBuilder {
    private StatusBuilder statusBuilder;
    private FileMatcher fileMatcher;

    public BasicResponseGenerator(StatusBuilder statusBuilder, FileMatcher fileMatcher) {
        this.statusBuilder = statusBuilder;
        this.fileMatcher = fileMatcher;
    }
    
    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }
    
    public HashMap<String, ResponseBuilder> methods() {
        HashMap<String, ResponseBuilder> methods = new HashMap<>();
        methods.put("POST", new PostRequestResponseBuilder(statusBuilder));
        methods.put("PUT", new PutRequestResponseBuilder(statusBuilder, fileMatcher));
        methods.put("DELETE", new DeleteRequestResponseBuilder(statusBuilder));
        
        return methods;
    }
    
    public void getMethodResponseGenerator(Request request, Options options) throws Exception {
        String requestMethod = request.getMethod();
        
        for (String method : methods().keySet()) {
            if (method.equals(requestMethod)) {
                methods().get(method).buildResponse(request, options);
            }
        }
    }

    public String getMatchedFileName(Request request) throws IOException {
        String filePath = request.getFilePath();
        String fileName = fileMatcher.matchRequestedFile(filePath);
        
        if (!filePath.equals(fileName)) {
            statusBuilder.setHTTPStatus(404);
            return fileName;
        } else
            statusBuilder.setHTTPStatus(200);
        return fileName;
    }
    
    public byte[] buildResponse(Request request, Options options) throws Exception {
        getMethodResponseGenerator(request, options);
        options.setOptions("GET");
        return readFileBytesFromPath("../cob_spec/public/" + getMatchedFileName(request));
    }
}