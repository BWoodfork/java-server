package com.company.Handler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParameterDecoderTest {
    private ParameterDecoder parameterDecoder;
    
    @Before
    public void setUp() throws Exception {
       parameterDecoder = new ParameterDecoder();
    }
    
    @Test
    public void returnsTheParametersEndpointAsFirstArrayElement() throws Exception {
        String request = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff"; ;
        
        String firstHalfOfRequest = "/parameters";
        
        assertEquals(firstHalfOfRequest, parameterDecoder.splitRequest(request)[0]);
    }
    
    @Test
    public void returnsTheRestOfTheRequest() throws Exception {
        String request = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        
        String secondHalfOfRequest = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        
        assertEquals(secondHalfOfRequest, parameterDecoder.splitRequest(request)[1]);
    } 
    
    @Test
    public void returnsTheSomething() throws Exception {
        String preParsedRequest = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String postParsedRequest = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        
        assertEquals(postParsedRequest, parameterDecoder.parseRequest(preParsedRequest));
    }
    
    @Test
    public void decodesAParameterRequest() throws Exception {
        String request = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        
        String decodedRequest = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?variable_2 = stuff";
        
        assertEquals(decodedRequest, parameterDecoder.decodeRequest(request));
    }
    
    @Test
    public void decodesAParameterRequestSeparateFromTheCobspecRequirement() throws Exception {
        String request = "/parameters?field1=value1&field2=value2&field3=value3";
        
        String decodedRequest = "field1 = value1&field2 = value2field3 = value3";

        assertEquals(decodedRequest, parameterDecoder.decodeRequest(request));
    }
    
    @Test
    public void returnsTrueIfRequestIsAParameterRequest() throws Exception {
        String request = "/parameters?variable_1=Operators%";

        assertEquals(true, parameterDecoder.isAParameterRequest(request));
    }

    @Test
    public void returnsFalseIfRequestIsNotAParameterRequest() throws Exception {
        String request = "/file1SomeOtherTextThatIsNotAParameterRequest";

        assertEquals(false, parameterDecoder.isAParameterRequest(request));
    }
}