package com.company.Response.ResponseGenerators;

import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PutRequestResponseGeneratorTest {
    
    private PutRequestIResponseGenerator putRequestResponseGenerator;
    
    @Before
    public void setUp() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        putRequestResponseGenerator = new PutRequestIResponseGenerator(statusBuilder);
    }
    
    @Test
    public void returnsTrueIfRequestIsAPUTRequest() throws Exception {
        String method = "PUT";

        assertEquals(true, putRequestResponseGenerator.isAPutRequest(method));
    }
    
    @Test
    public void returnsTrueIfRequestIsAGetRequest() throws Exception {
        String method = "GET";

        assertEquals(true, putRequestResponseGenerator.isAGetRequest(method));
    }
    
    @Test
    public void returnsFalseIfRequestIsNotAPUTRequest() throws Exception {
        String method = "PATCH";

        assertEquals(false, putRequestResponseGenerator.isAPutRequest(method));
    }
    
    @Test
    public void writesToFileWhenAPostRequestIsMade() throws Exception {
        Request request = new Request("PUT /form");
        putRequestResponseGenerator.getBody(request);
        
        assertEquals("data=heathcliff", new String(putRequestResponseGenerator.getPutContentFile()));
    }
    
    @Test
    public void returnsTheFileWhenAGetRequestIsMade() throws Exception {
        Request request = new Request("GET /form");
        putRequestResponseGenerator.getBody(request);
        
        assertEquals("data=heathcliff", new String(putRequestResponseGenerator.getPutContentFile()));
    }
    
    @Test
    public void returnsErrorMessageIfNotAPostOrGetRequest() throws Exception {
        Request request = new Request("POST /form");
        putRequestResponseGenerator.getBody(request);

        assertEquals("The Requested Endpoint Is Not A PUT Request", new String(putRequestResponseGenerator.getBody(request)));
    }
}