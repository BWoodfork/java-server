package com.company;

import Reponse.HTTPResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPResponseTest {
    private HTTPResponse response;

    @Before
    public void setUp() throws Exception {
        response = new HTTPResponse();
    }

    @Test
    public void get200OKResponseForFile1Request() throws Exception {
        String request = "GET /file1 HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("405 OK", response.getResponseStatus(request));
    }

    @Test
    public void get200OKResponseForFile2Request() throws Exception {
        String request = "GET /file2 HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("200 OK", response.getResponseStatus(request));
    }

    @Test
    public void get200OKResponseForMethodOptionsRequest() throws Exception {
        String request = "GET /method_options HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("200 OK", response.getResponseStatus(request));
    }

    @Test
    public void get200OKForFormRequest() throws Exception {
        String request = "GET /form HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("200 OK", response.getResponseStatus(request));
    }

    @Test
    public void get405MethodNotAllowedForTextFileRequest() throws Exception {
        String request = "GET /text-file.txt HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("405 Method Not Allowed", response.getResponseStatus(request));
    }

    @Test
    public void get301MovedPermanentlyForRedirectRequest() throws Exception {
        String request = "GET /redirect HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("301 Moved Permanently", response.getResponseStatus(request));
    }

    @Test
    public void get401UnauthorizedMessage() throws Exception {
        String request = "GET /logs HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("401 Unauthorized", response.getResponseStatus(request));
    }

    @Test
    public void get200OKWhenAuthorizedRequest() throws Exception {
        String request = "GET /logs HTTP/1.1Authorization: Basic YWRtaW46aHVudGVyMg==Connection: closeHost: localhost:5000";

        assertEquals("200 OK", response.getResponseStatus(request));
    }

    @Test
    public void get204NoContentWhenPATCHRequestIsSent() throws Exception {
        String request = "PATCH /patch-content.txt HTTP/1.1Content-Length: 15If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4Connection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded";

        assertEquals("204 No Content", response.getResponseStatus(request));
    }

    @Test
    public void get404NotFoundResponse() throws Exception {
        String request = "GET /foobar HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("404 NOT FOUND", response.getResponseStatus(request));
    }
}