package com.company.Response;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPStatusCodesTest {
    private com.company.Response.HTTPStatusCodes response;

    @Before
    public void setUp() throws Exception {
        response = new com.company.Response.HTTPStatusCodes();
    }

    @Test
    public void get200OKResponseForFile1Request() throws Exception {
        String requestPath = "/file1";
        String method = "GET";
        String data = "closeHost";

        assertEquals("405 OK", response.getHTTPStatusCode(method, requestPath, data));
    }

    @Test
    public void get200OKResponseForFile2Request() throws Exception {
        String requestPath = "/file2";
        String method = "GET";
        String data = "closeHost";

        assertEquals("200 OK", response.getHTTPStatusCode(method, requestPath, data));
    }

    @Test
    public void get200OKResponseForMethodOptionsRequest() throws Exception {
        String requestPath = "/method_options";
        String method = "GET";
        String data = "closeHost";

        assertEquals("200 OK", response.getHTTPStatusCode(method, requestPath, data));
    }

    @Test
    public void get200OKForFormRequest() throws Exception {
        String requestPath = "/form";
        String method = "GET";
        String data = "closeHost";

        assertEquals("200 OK", response.getHTTPStatusCode(method, requestPath, data));
    }

    @Test
    public void get405MethodNotAllowedForTextFileRequest() throws Exception {
        String requestPath = "/text-file.txt";
        String method = "GET";
        String data = "closeHost";

        assertEquals("405 Method Not Allowed", response.getHTTPStatusCode(method, requestPath, data));
    }

    @Test
    public void get301MovedPermanentlyForRedirectRequest() throws Exception {
        String requestPath = "/redirect";
        String method = "GET";
        String data = "closeHost";

        assertEquals("301 Moved Permanently", response.getHTTPStatusCode(method, requestPath, data));
    }

    @Test
    public void get401UnauthorizedMessage() throws Exception {
        String requestPath = "/logs";
        String method = "GET";
        String data = "localhost:5000";

        assertEquals("401 Unauthorized", response.getHTTPStatusCode(method, requestPath, data));
    }

    @Test
    public void get200OKWhenAuthorizedRequest() throws Exception {
        String requestPath = "/logs";
        String method = "GET";
        String data = "YWRtaW46aHVudGVyMg";

        assertEquals("200 OK", response.getHTTPStatusCode(method, requestPath, data));
    }

    @Test
    public void get204NoContentWhenPATCHRequestIsSent() throws Exception {
        String requestPath = "/patch-content.txt";
        String method = "PATCH";
        String data = "60bb224c68b1ed765a0f84d910de58d0beea91c4Connection";

        assertEquals("204 No Content", response.getHTTPStatusCode(method, requestPath, data));
    }
    
    @Test
    public void get200oKWhenGetRequestToPatchContent() throws Exception {
        String requestPath = "/patch-content.txt";
        String method = "GET";
        String data = "69bc18dc1edc9e1316348b2eaaca9df83898249f";
        
        assertEquals("200 OK", response.getHTTPStatusCode(method, requestPath, data));
    }

    @Test
    public void get404NotFoundResponse() throws Exception {
        String requestPath = "/foobar";
        String method = "GET";
        String data = "localhost:5000";

        assertEquals("404 NOT FOUND", response.getHTTPStatusCode(method, requestPath, data));
    }
    
    @Test
    public void get206PartialResponse() throws Exception {
        String requestPath = "/partial_content.txt";
        String method = "GET";
        String data = "localhost:5000";
        
        assertEquals("206 Partial Content", response.getHTTPStatusCode(method, requestPath, data));
    }
}