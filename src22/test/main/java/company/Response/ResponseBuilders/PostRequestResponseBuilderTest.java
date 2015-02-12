package main.java.company.Response.ResponseBuilders;

import main.java.company.Response.Headers.Options;
import main.java.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostRequestResponseBuilderTest {
    private PostRequestResponseBuilder postRequestResponseGenerator;
    private StatusBuilder statusBuilder;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        postRequestResponseGenerator = new PostRequestResponseBuilder(statusBuilder);
    }
    
    @Test
    public void returnsTrueIfRequestIsAPostRequest() throws Exception {
        String method = "POST";

        assertEquals(true, postRequestResponseGenerator.isAPostRequest(method));
    }
    
    @Test
    public void returnsFalseIfRequestIsNotAPostRequest() throws Exception {
        String method = "PATCH";

        assertEquals(false, postRequestResponseGenerator.isAPostRequest(method));
    }
    
    @Test
    public void returnsErrorMessageIfNotAPostOrGetRequest() throws Exception {
        Request request = new Request("PUT /form");
        Options options = new Options();
        postRequestResponseGenerator.buildResponse(request, options);

        assertEquals("The Requested Endpoint Is Not A POST Request", new String(postRequestResponseGenerator.buildResponse(request, options)));
    }
    
    @Test
    public void setsTheStatusTo200OkWhenPostRequestComesThrough() throws Exception {
        Request request = new Request("POST /form");
        Options options = new Options();
        postRequestResponseGenerator.buildResponse(request, options);
        
        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void setsAllowHeaderToPOSTWhenPostRequestIsMade() throws Exception {
        Request request = new Request("POST /form");
        Options options = new Options();
        postRequestResponseGenerator.buildResponse(request, options);

        assertEquals("Allow: POST\r\n", options.getOptions());
    }
}