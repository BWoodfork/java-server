package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPResponseTest {
    @Test
    public void get200OKResponse() throws Exception {
        HTTPResponse response = new HTTPResponse();

        String path = "/file1";

        assertEquals("200 OK", response.getResponseStatus(path));
    }

    @Test
    public void get200OKResponseFromFile2() throws Exception {
        HTTPResponse response = new HTTPResponse();

        String path = "/file2";

        assertEquals("200 OK", response.getResponseStatus(path));
    }

    @Test
    public void get404NotFoundResponse() throws Exception {
        HTTPResponse response = new HTTPResponse();

        String path = "/foobar";

        assertEquals("404 NOT FOUND", response.getResponseStatus(path));
    }
}