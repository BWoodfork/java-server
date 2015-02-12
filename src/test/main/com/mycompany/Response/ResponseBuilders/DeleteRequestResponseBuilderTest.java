package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Handler.PutRequestHandler;
import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.Utilities.FileMatcher;
import main.com.mycompany.request.Request;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class DeleteRequestResponseBuilderTest {
    private DeleteRequestResponseBuilder deleteRequestResponseGenerator;
    private StatusBuilder statusBuilder;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        deleteRequestResponseGenerator = new DeleteRequestResponseBuilder(statusBuilder);
    }
    
    @Test
    public void returnsTrueIfMethodIsADeleteRequest() throws Exception {
        String method = "DELETE";
        
        assertEquals(true, deleteRequestResponseGenerator.isADeleteRequest(method));
    }
    
    @Test
    public void returnsFalseIfMethodIsNotADeleteRequest() throws Exception {
        String method = "PATCH";
        
        assertEquals(false, deleteRequestResponseGenerator.isADeleteRequest(method));
    }
    
    @Test
    public void deletesContentFromPath() throws  Exception {
        Request request = new Request("PUT /form");
        Options options = new Options();
        PutRequestHandler putRequestHandler = new PutRequestHandler();
        FileMatcher fileMatcher = new FileMatcher();
        PutRequestResponseBuilder putRequestResponseGenerator = new PutRequestResponseBuilder(statusBuilder, fileMatcher);
        putRequestHandler.execute();
        putRequestResponseGenerator.buildResponse(request, options);
        
        assertEquals("data=heathcliff", new String(putRequestResponseGenerator.getPutContentFile(request)));
        
        Request request2 = new Request("DELETE /form");
        deleteRequestResponseGenerator.buildResponse(request2, options);
        Path path = Paths.get("../cob_spec/public/cosby-data.txt").toAbsolutePath();
        
        assertEquals("", new String(Files.readAllBytes(path)));
    }
    
    @Test
    public void returnsErrorMessageIfNotADeleteRequest() throws Exception {
        Request request = new Request("POST /form");
        Options options = new Options();
        
        assertEquals("The Requested Endpoint Is Not A DELETE Request", new String(deleteRequestResponseGenerator.buildResponse(request, options)));
    }
    
    @Test
    public void returns200OkStatusWhenSuccessfulDeleteRequestIsMade() throws Exception {
        Request request = new Request("DELETE");
        Options options = new Options();
        deleteRequestResponseGenerator.buildResponse(request, options);
        
        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void returnsAllowHeaderWhenSuccessfulDELETERequestIsMade() throws Exception {
        Request request = new Request("DELETE /form");
        Options options = new Options();
        deleteRequestResponseGenerator.buildResponse(request, options);
        
        assertEquals("Allow: DELETE\r\n", options.getOptions());
    }
}