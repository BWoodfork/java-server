package com.company.Handler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParameterDecoderTest {
    @Test
    public void throwsErrorIfNotAParameterRequest() throws Exception {
        ParameterDecoder parameterDecoder = new ParameterDecoder();
        String request = "/someOtherRequest?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";

        assertEquals("The page that you're looking for cannot be found", new String(parameterDecoder.parseRequest(request)));
    }
    
    @Test
    public void splitsTheIncomingRequest() throws Exception {
        ParameterDecoder parameterDecoder = new ParameterDecoder();
        String request = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String decodedValue = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?variable_2 = stuff";
        
        assertEquals(decodedValue, new String(parameterDecoder.parseRequest(request)));
    }
}