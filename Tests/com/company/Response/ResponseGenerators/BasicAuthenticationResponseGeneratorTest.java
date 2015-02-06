package com.company.Response.ResponseGenerators;

import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicAuthenticationResponseGeneratorTest {
    private BasicAuthenticationIResponseGenerator basicAuthenticationResponseGenerator;
    
    @Before
    public void setUp() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        basicAuthenticationResponseGenerator = new BasicAuthenticationIResponseGenerator(statusBuilder);
    }
    
    @Test
    public void returnsTrueIfRequestHasCredentials() throws Exception {
        Request request = new Request("GET /logs HTTP/1.1Authorization: Basic YWRtaW46aHVudGVyMg==Connection: closeHost: localhost:5000");
        String data = request.getData();
        
        assertEquals(true, basicAuthenticationResponseGenerator.hasCredentials(data));
    }
    
    @Test
    public void returnsFalseIfRequestDoesNotHaveCredentials() throws Exception {
        Request request = new Request("GET /logs HTTP/1.1Connection: closeHost: localhost:5000");
        String data = request.getData();
        
        assertEquals(false, basicAuthenticationResponseGenerator.hasCredentials(data));
    }
}