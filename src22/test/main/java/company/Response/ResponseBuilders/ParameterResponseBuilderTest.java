package main.java.company.Response.ResponseBuilders;

import main.java.company.Response.Headers.Options;
import main.java.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParameterResponseBuilderTest {
    private StatusBuilder statusBuilder;
    private ParameterResponseBuilder parameterResponseBuilder;
    private Options options;
    private Request paramRequest;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        parameterResponseBuilder = new ParameterResponseBuilder(statusBuilder);
        options = new Options();
        paramRequest = new Request("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");
    }
    
    @Test
    public void returnsTheBodyIfRequestIsAParameterRequest() throws Exception {
        String decodedVariables = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?variable_2 = stuff";
        
        assertEquals(decodedVariables, new String(parameterResponseBuilder.buildResponse(paramRequest, options)));
    }
    
    @Test
    public void returnsErrorMessageIfNotAParameterRequest() throws Exception {
        Request request = new Request("GET /SomethingElse?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");

        assertEquals("The Requested Endpoint Is Not A PATCH Request", new String(parameterResponseBuilder.buildResponse(request, options)));
    }
    
    @Test
    public void returns200OkStatusMessageWhenSuccessfulParameterRequestIsMade() throws Exception {
        parameterResponseBuilder.buildResponse(paramRequest, options);
        
        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void returnsGETOnMethodOptionsWhenSuccessfulRequestIsMade() throws Exception {
        parameterResponseBuilder.buildResponse(paramRequest, options);
        
        assertEquals("Allow: GET\r\n", options.getOptions());
    }
}