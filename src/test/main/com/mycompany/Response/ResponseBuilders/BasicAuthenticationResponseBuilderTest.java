package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicAuthenticationResponseBuilderTest {
    private BasicAuthenticationResponseBuilder basicAuthenticationResponseGenerator;
    private StatusBuilder statusBuilder;
    private Request request;
    private Request unauthorizedRequest;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        basicAuthenticationResponseGenerator = new BasicAuthenticationResponseBuilder(statusBuilder);
        unauthorizedRequest = new Request("GET /logs HTTP/1.1Connection: closeHost: localhost:5000");
        request = new Request("GET /logs HTTP/1.1Authorization: Basic YWRtaW46aHVudGVyMg==Connection: closeHost: localhost:5000");
    }
    
    @Test
    public void returnsTrueIfRequestHasCredentials() throws Exception {
        String data = request.getData();

        assertEquals(true, basicAuthenticationResponseGenerator.hasCredentials(data));
    }
    
    @Test
    public void returnsFalseIfRequestDoesNotHaveCredentials() throws Exception {
        String data = unauthorizedRequest.getData();

        assertEquals(false, basicAuthenticationResponseGenerator.hasCredentials(data));
    }
    
    @Test
    public void setsTheStatusTo200OKWhenSuccessfulBasicAuthRequestIsMade() throws Exception {
        Options options = new Options();
        basicAuthenticationResponseGenerator.buildResponse(request, options);
        
        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void setsTheStatusTo401WhenAnUnsuccessfulAuthRequestIsMade() throws Exception {
        Options options = new Options();
        basicAuthenticationResponseGenerator.buildResponse(unauthorizedRequest, options);
        
        assertEquals("HTTP/1.1 401 Unauthorized\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void setsTheAllowHeaderToGetPutHeadWhenBasicAuthRequestIsMade() throws Exception {
        Options options = new Options();
        basicAuthenticationResponseGenerator.buildResponse(request, options);
        
        assertEquals("Allow: GET,PUT,HEAD\r\n", options.getOptions());
    }
}