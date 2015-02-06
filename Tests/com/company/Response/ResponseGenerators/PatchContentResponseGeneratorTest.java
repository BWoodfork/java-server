package com.company.Response.ResponseGenerators;

import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PatchContentResponseGeneratorTest {
    private PatchContentResponseGenerator patchContentResponseGenerator;
    
    @Before
    public void setUp() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        patchContentResponseGenerator = new PatchContentResponseGenerator(statusBuilder);
    }
    
    @Test
    public void returnsTrueIfRequestIsAPatchRequest() throws Exception {
        Request request = new Request("PATCH /patch-content.txt HTTP/1.1Content-Length: 15If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4Connection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded");
        
        assertEquals(true, patchContentResponseGenerator.isAPatchRequest(request));
    }
    
    @Test
    public void returnsFalseIfRequestIsNotAPatchRequest() throws Exception {
        Request request = new Request("GET /patch-content.txt HTTP/1.1Content-Length: 15If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4Connection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded");
        
        assertEquals(false, patchContentResponseGenerator.isAPatchRequest(request));
    }
    
    @Test
    public void returnsEtagFromRequest() throws Exception {
        Request request = new Request("PATCH /patch-content.txt HTTP/1.1Content-Length: 15If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4Connection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded");

        assertEquals("60bb224c68b1ed765a0f84d910de58d0beea91c4" , patchContentResponseGenerator.getEtag(request));
    }
}