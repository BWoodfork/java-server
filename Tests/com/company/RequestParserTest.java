package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser parser;

    @Before
    public void setUp() throws Exception {
        String getRequest = "GET / HTTP/1.1 Authorization:Basic YWRtaW46aHVudGVyMg==Connection: closeHost: localhost:5000";
        parser = new RequestParser(getRequest);
    }

    @Test
    public void parseRequest() throws Exception {
        String[] expected = {"GET", "/", "HTTP/1.1", "Authorization:Basic", "YWRtaW46aHVudGVyMg==Connection:", "closeHost:", "localhost:5000"};

        assertEquals(expected, parser.parseRequest());
    }

    @Test
    public void getMethod() throws Exception {
        assertEquals("GET", parser.getMethod());
    }

    @Test
    public void getFilePath() throws Exception {
        assertEquals("/", parser.getFilePath());
    }

    @Test
    public void getAuthenticationData() throws Exception {
        assertEquals("YWRtaW46aHVudGVyMg==Connection:", parser.getAuthenticationData());
    }
}
