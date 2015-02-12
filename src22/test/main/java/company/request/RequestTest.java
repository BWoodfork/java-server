package main.java.company.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RequestTest {
    private Request request;
   
    @Before
    public void setUp() throws Exception {
        request = new Request("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000");
    }
    
    @Test
    public void returnsTheRequestAsAStringArray() throws Exception {
        String[] splitRequest = {"GET", "/partial_content.txt", "HTTP/1.1Range:", "bytes=0-4Connection:", "closeHost:", "localhost:5000"};
        
        assertArrayEquals(splitRequest, request.parseRequest());
    }

    @Test
    public void getsTheIncomingRequestsHTTPMethod() throws Exception {
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void getsTheIncomingRequestsFilePath() throws Exception {
        assertEquals("/partial_content.txt", request.getFilePath());
    }
    
    @Test
    public void getsTheIncomingByteCountForRequestsThatRequireIt() throws Exception {
        assertEquals("bytes=0-4Connection:", request.getByteCount());
    }

    @Test
    public void getsTheLocalHostDataOfTheIncomingRequest() throws Exception {
        assertEquals("closeHost:", request.getData());
    }
    
}