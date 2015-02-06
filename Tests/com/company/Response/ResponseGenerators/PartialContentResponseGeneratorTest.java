package com.company.Response.ResponseGenerators;

import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentResponseGeneratorTest {
    private StatusBuilder statusBuilder;
    private PartialContentResponseGenerator partialContentResponseGenerator;
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        partialContentResponseGenerator = new PartialContentResponseGenerator(statusBuilder);
    }
    
    @Test
    public void returnsTrueIfRequestIsAPartialRequest() throws Exception {
        Request request = new Request("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000");

        assertEquals(true, partialContentResponseGenerator.isAPartialRequest(request));
    }

    @Test
    public void returnsFalseIfRequestIsNotAPartialRequest() throws Exception {
        Request request = new Request("GET /partial_content.txt HTTP/1.1Range: loseHost: localhost:5000");

        assertEquals(false, partialContentResponseGenerator.isAPartialRequest(request));
    }
    
    @Test
    public void returnsTheBodyWhenAPartialRequestHasBeenMade() throws Exception {
        Request request = new Request("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000");

        assertEquals("This ", new String(partialContentResponseGenerator.getBody(request)));
    }
    
    @Test
    public void returnsErrorMessageWhenRequestIsNotAPartialRequest() throws Exception {
        Request request = new Request("GET /partial_content.txt HTTP/1.1Range: Connection: closeHost: localhost:5000");

        assertEquals("The requested endpoint does not exist", new String(partialContentResponseGenerator.getBody(request)));
    }
}