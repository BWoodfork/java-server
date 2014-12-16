package com.company;

import Reponse.HTTPResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPResponseTest {
    @Test
    public void get200OKResponse() throws Exception {
        HTTPResponse response = new HTTPResponse();


        String path = "GET /file1 HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("405 OK", response.getResponseStatus(path));
    }

    @Test
    public void get200OKResponseFromFile2() throws Exception {
        HTTPResponse response = new HTTPResponse();

        String path = "GET /file2 HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("200 OK", response.getResponseStatus(path));
    }

    @Test
    public void get404NotFoundResponse() throws Exception {
        HTTPResponse response = new HTTPResponse();

        String path = "GET /foobar HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("404 NOT FOUND", response.getResponseStatus(path));
    }

    @Test
    public void get401UnauthorizedMessage() throws Exception {
        HTTPResponse response = new HTTPResponse();

        String path = "GET /logs HTTP/1.1Connection: closeHost: localhost:5000";

        assertEquals("401 Unauthorized", response.getResponseStatus(path));
    }

    @Test
    public void get200OKWhenAuthorizedRequest() throws Exception {
        HTTPResponse response = new HTTPResponse();

        String path = "GET /logs HTTP/1.1Authorization: Basic YWRtaW46aHVudGVyMg==Connection: closeHost: localhost:5000";

        assertEquals("200 OK", response.getResponseStatus(path));
    }
}