package com.company.Response.ResponseGenerators;

import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostRequestResponseGeneratorTest {
    private PostRequestIResponseGenerator postRequestResponseGenerator;
    
    @Before
    public void setUp() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        postRequestResponseGenerator = new PostRequestIResponseGenerator(statusBuilder);
    }
    
    @Test
    public void returnsTrueIfRequestIsAPostRequest() throws Exception {
        String method = "POST";

        assertEquals(true, postRequestResponseGenerator.isAPostRequest(method));
    }
    
    @Test
    public void returnsTrueIfRequestIsAGetRequest() throws Exception {
        String method = "GET";

        assertEquals(true, postRequestResponseGenerator.isAGetRequest(method));
    }
    
    @Test
    public void returnsFalseIfRequestIsNotAPostRequest() throws Exception {
        String method = "PATCH";

        assertEquals(false, postRequestResponseGenerator.isAPostRequest(method));
    }
    
    @Test
    public void writesToFileWhenAPostRequestIsMade() throws Exception {
        Request request = new Request("POST /form");
        postRequestResponseGenerator.getBody(request);
        
        assertEquals("data=cosby", new String(postRequestResponseGenerator.getPostContentFile()));
    }
    
    @Test
    public void returnsTheFileWhenAGetRequestIsMade() throws Exception {
        Request request = new Request("GET /form");
        postRequestResponseGenerator.getBody(request);
        
        assertEquals("data=cosby", new String(postRequestResponseGenerator.getPostContentFile()));
    }
    
    @Test
    public void returnsErrorMessageIfNotAPostOrGetRequest() throws Exception {
        Request request = new Request("PUT /form");
        postRequestResponseGenerator.getBody(request);

        assertEquals("The Requested Endpoint Is Not A Post Request", new String(postRequestResponseGenerator.getBody(request)));
    }
}