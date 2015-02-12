package main.java.company.Response.ResponseBuilders;

import main.java.company.Response.Headers.Options;
import main.java.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsResponseBuilderTest {
    private OptionsResponseBuilder optionsResponseBuilder;
    private StatusBuilder statusBuilder;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        optionsResponseBuilder = new OptionsResponseBuilder(statusBuilder);
    }
    
    @Test
    public void returnsTrueIfMethodIsAnOptionsRequest()throws Exception {
        String method = "OPTIONS";
        
        assertEquals(true, optionsResponseBuilder.isAnOptionsRequest(method));
    }
    
    @Test
    public void returnsFalseIfMethodIsNotAnOptionsRequest() throws Exception {
        String method = "POST";
        
        assertEquals(false, optionsResponseBuilder.isAnOptionsRequest(method));
    }
    
    @Test
    public void setsStatusTo200OkWhenOptionsRequestIsMade() throws Exception {
        Request request = new Request("OPTIONS /method_options");
        Options options = new Options();
        optionsResponseBuilder.buildResponse(request, options);
        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void returnsErrorMessageIfRequestIsNotAnOptionsRequest() throws Exception {
        Request request = new Request("POST /method_options");
        Options options = new Options();
        
        assertEquals("The Requested Endpoint Is Not A PATCH Request", new String(optionsResponseBuilder.buildResponse(request, options)));
    }
}