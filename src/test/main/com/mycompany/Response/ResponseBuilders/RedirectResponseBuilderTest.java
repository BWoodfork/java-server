package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectResponseBuilderTest {
    RedirectResponseBuilder redirectResponseBuilder;
    StatusBuilder statusBuilder;
    Request request;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        redirectResponseBuilder = new RedirectResponseBuilder(statusBuilder);
        request = new Request("GET /redirect");
    }
    
    @Test
    public void returns301ContentMovedWhenSuccessfulRequestIsMade() throws Exception {
        Options options = new Options();
        redirectResponseBuilder.buildResponse(request, options);
        
        assertEquals("HTTP/1.1 301 Moved Permanently\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void returnsGetInAllowHeaderWhenSuccessfulRequestIsMade() throws Exception {
        Options options = new Options();
        redirectResponseBuilder.buildResponse(request, options);

        assertEquals("Allow: GET\r\n", options.getOptions());
    }
}