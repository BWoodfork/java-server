package com.company.Response.ResponseGenerators;

import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParameterResponseGeneratorTest {
    @Test
    public void returnsTheBodyIfRequestIsAParameterRequest() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        ParameterIResponseGenerator parameterResponseGenerator = new ParameterIResponseGenerator(statusBuilder);
        Request request = new Request("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");
        String decodedVariables = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?variable_2 = stuff";
        
        assertEquals(decodedVariables, new String(parameterResponseGenerator.getBody(request)));
    }
    
    @Test
    public void returnsErrorMessageIfNotAParameterRequest() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        ParameterIResponseGenerator parameterResponseGenerator = new ParameterIResponseGenerator(statusBuilder);
        Request request = new Request("GET /SomethingElse?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000");

        assertEquals("The requested endpoint cannot be found", new String(parameterResponseGenerator.getBody(request)));
    }
}