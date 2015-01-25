package com.company.request;

import com.company.Response.RequestBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    private Request request;
    private RequestBuilder requestBuilder;
    private FakeSocket fakeSocket;
    
    public class FakeSocket extends Socket {
        private final String response;

        public FakeSocket(String s) {
            response = s;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(response.getBytes());
        }
    }
   
    @Before
    public void setUp() throws Exception {
        requestBuilder = new RequestBuilder();
        request = new Request(requestBuilder);
        fakeSocket = new FakeSocket("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000");
    }
    
    @Test
    public void returnsRequestAsAString() throws Exception {
        assertEquals("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000", request.getRequest(fakeSocket));
    }
    
    @Test
    public void getsTheIncomingRequestsHTTPMethod() throws Exception {
        Request request = new Request(requestBuilder);

        assertEquals("GET", request.getMethod(fakeSocket));
    }
    
    @Test
    public void getsTheIncomingRequestsFilePath() throws Exception {
        Request request = new Request(requestBuilder);
        
        assertEquals("/file1", request.getFilePath(fakeSocket));
    }
    
    @Test
    public void getsTheIncomingByteCountForRequestsThatRequireIt() throws Exception {
        Request request = new Request(requestBuilder);
        
        assertEquals("closeHost:", request.getByteCount(fakeSocket));
    }
    
    @Test
    public void getsTheLocalHostDataOfTheIncomingRequest() throws Exception {
        Request request = new Request(requestBuilder);
        
        assertEquals("localhost:5000", request.getData(fakeSocket));
    }
    
}