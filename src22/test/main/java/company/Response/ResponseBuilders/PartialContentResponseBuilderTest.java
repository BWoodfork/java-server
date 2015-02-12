package main.java.company.Response.ResponseBuilders;

import main.java.company.Response.Headers.Options;
import main.java.company.Utilities.FileMatcher;
import main.java.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentResponseBuilderTest {
    private StatusBuilder statusBuilder;
    private PartialContentResponseBuilder partialContentResponseBuilder;
    private Request partialRequest;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        FileMatcher fileMatcher = new FileMatcher();
        partialContentResponseBuilder = new PartialContentResponseBuilder(statusBuilder, fileMatcher);
        partialRequest = new Request("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000");
    }
    
    @Test
    public void returnsTrueIfRequestIsAPartialRequest() throws Exception {
        assertEquals(true, partialContentResponseBuilder.isAPartialRequest(partialRequest));
    }

    @Test
    public void returnsFalseIfRequestIsNotAPartialRequest() throws Exception {
        Request request = new Request("GET /partial_content.txt HTTP/1.1Range: loseHost: localhost:5000");

        assertEquals(false, partialContentResponseBuilder.isAPartialRequest(request));
    }
    
    @Test
    public void returnsTheBodyWhenAPartialRequestHasBeenMade() throws Exception {
        Options options = new Options();
        assertEquals("This ", new String(partialContentResponseBuilder.buildResponse(partialRequest, options)));
    }

    @Test
    public void returnsErrorMessageWhenRequestIsNotAPartialRequest() throws Exception {
        Request request = new Request("GET /partial_content.txt HTTP/1.1Range: Connection: closeHost: localhost:5000");
        Options options = new Options();
        assertEquals("The Requested Endpoint Is Not A PATCH Request", new String(partialContentResponseBuilder.buildResponse(request, options)));
    }
    
    @Test
    public void returns206PartialContentWhenSuccessfulRequestIsMade() throws Exception {
        Options options = new Options();
        partialContentResponseBuilder.buildResponse(partialRequest, options);
        assertEquals("HTTP/1.1 206 Partial Content\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void returnsGetInTheAllowHeaderWhenSuccessfulRequestIsMade() throws Exception {
        Options options = new Options();
        partialContentResponseBuilder.buildResponse(partialRequest, options);
        
        assertEquals("Allow: GET\r\n", options.getOptions());
    }
}