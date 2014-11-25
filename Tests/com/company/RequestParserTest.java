package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private String getRequest;
    private RequestParser parser;

//    @Before
//    public void setUp() throws Exception {
//        getRequest = "GET / HTTP/1.1";
//        parser = new RequestParser(getRequest);
//    }
//
//    @Test
//    public void parseRequest() throws Exception {
//        String[] expected = {"GET", "/", "HTTP/1.1"};
//
//        assertEquals(expected, parser.parseRequest());
//    }

    @Test
    public void getMethod() throws Exception {
        assertEquals("GET", parser.getMethod());
    }

    @Test
    public void getFilePath() throws Exception {
        assertEquals("/", parser.getFilePath());
    }
}
