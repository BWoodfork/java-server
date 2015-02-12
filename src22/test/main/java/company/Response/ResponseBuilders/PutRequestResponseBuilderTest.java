package main.java.company.Response.ResponseBuilders;

import main.java.company.Response.Headers.Options;
import main.java.company.Utilities.FileMatcher;
import main.java.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PutRequestResponseBuilderTest {
    
    private PutRequestResponseBuilder putRequestResponseGenerator;
    private StatusBuilder statusBuilder;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        FileMatcher fileMatcher = new FileMatcher();
        putRequestResponseGenerator = new PutRequestResponseBuilder(statusBuilder, fileMatcher);
    }
    
    @Test
    public void returnsTrueIfRequestIsAPUTRequest() throws Exception {
        String method = "PUT";

        assertEquals(true, putRequestResponseGenerator.isAPutRequest(method));
    }
    
    @Test
    public void returnsTrueIfRequestIsAGetRequest() throws Exception {
        String method = "GET";

        assertEquals(true, putRequestResponseGenerator.isAGetRequest(method));
    }
    
    @Test
    public void returnsFalseIfRequestIsNotAPUTRequest() throws Exception {
        String method = "PATCH";

        assertEquals(false, putRequestResponseGenerator.isAPutRequest(method));
    }
    
    @Test
    public void writesToFileWhenAPostRequestIsMade() throws Exception {
        Request request = new Request("PUT /form");
        Options options = new Options();
        putRequestResponseGenerator.buildResponse(request, options);
        
        assertEquals("data=heathcliff", new String(putRequestResponseGenerator.getPutContentFile(request)));
    }
    
    @Test
    public void returnsErrorMessageIfNotAPostOrGetRequest() throws Exception {
        Request request = new Request("POST /form");
        Options options = new Options();
        putRequestResponseGenerator.buildResponse(request, options);

        assertEquals("The Requested Endpoint Is Not A PUT Request", new String(putRequestResponseGenerator.buildResponse(request, options)));
    }
    
    @Test
    public void returns200OkStatusWhenSuccessfulRequestIsMade() throws Exception {
        Request request = new Request("PUT /form");
        Options options = new Options();
        putRequestResponseGenerator.buildResponse(request, options);
        
        assertEquals("HTTP/1.1 200 OK\r\n" , new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void returnsPutInTheAllowHeaderWhenSuccessfulRequestIsMade() throws Exception {
        Request request = new Request("PUT /form");
        Options options = new Options();
        putRequestResponseGenerator.buildResponse(request, options);
        
        assertEquals("Allow: PUT\r\n", options.getOptions());
    }
}