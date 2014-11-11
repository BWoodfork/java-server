package com.company;

import junit.framework.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SocketServiceTest {

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

    @Test
    public void makeGetRequest() throws Exception {
        SocketService service = new SocketService();
        Socket fakeSocket = new FakeSocket("GET HTTP/1.1");

        Assert.assertEquals("GET HTTP/1.1", service.getRequest(fakeSocket));
    }

    @Test
    public void getRequest() throws Exception {
        SocketService service = new SocketService();
        Socket fakeSocket = new FakeSocket("GET HTTP/1.1");
        String expected = "GET HTTP/1.1";

        Assert.assertEquals(expected, service.getRequest(fakeSocket));
    }

    @Test
    public void getContentLength() throws Exception {
        SocketService service = new SocketService();
        int number = 37;
        String content = "<html><body> Hello World <body><html>";

        Assert.assertEquals(number, service.getContentLength(content));
    }

//    @Test
//    public void testThis() throws Exception {
//        SocketService service = new SocketService();
//        String string = "Hello";
//        Socket fakeSocket = new FakeSocket("GET HTTP/1.1");
//
//        Assert.assertEquals(string, service.testThis(fakeSocket));
//    }
}