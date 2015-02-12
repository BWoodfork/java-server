package main.java.company.Handler;

import main.java.company.request.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParameterRequestHandlerTest {
    private ParameterRequestHandler parameterRequestHandler;
    private Request request;
    
    @Test
    public void returnsTheParametersEndpointAsFirstArrayElement() throws Exception {
        request = new Request("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");
        parameterRequestHandler = new ParameterRequestHandler();
        String firstHalfOfRequest = "/parameters";
        
        assertEquals(firstHalfOfRequest, parameterRequestHandler.splitRequest(request)[0]);
    }
    
    @Test
    public void returnsTheRestOfTheRequest() throws Exception {
        request = new Request("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");
        parameterRequestHandler = new ParameterRequestHandler();
        String secondHalfOfRequest = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";

        assertEquals(secondHalfOfRequest, parameterRequestHandler.splitRequest(request)[1]);
    }
    
    @Test
    public void returnsTheEncodedStringOfTheParameterRequest() throws Exception {
        request = new Request("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");
        parameterRequestHandler = new ParameterRequestHandler();
        String postParsedRequest = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";

        assertEquals(postParsedRequest, parameterRequestHandler.parseRequest(request));
    }
    
    @Test
    public void decodesAParameterRequest() throws Exception {
        request = new Request("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");
        parameterRequestHandler = new ParameterRequestHandler();
        String decodedRequest = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?variable_2 = stuff";

        assertEquals(decodedRequest, parameterRequestHandler.decodeRequest(request));
    }
    
    @Test
    public void decodesAParameterRequestSeparateFromTheCobspecRequirement() throws Exception {
        Request alternateRequest = new Request("GET /parameters?field1=value1&field2=value2&field3=value3");
        parameterRequestHandler = new ParameterRequestHandler();
        String decodedRequest = "field1 = value1&field2 = value2field3 = value3";

        assertEquals(decodedRequest, parameterRequestHandler.decodeRequest(alternateRequest));
    }
}