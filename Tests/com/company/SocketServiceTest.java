package com.company;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

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

        assertEquals("GET HTTP/1.1", service.getRequest(fakeSocket));
    }

    @Test
    public void getRequest() throws Exception {
        SocketService service = new SocketService();
        Socket fakeSocket = new FakeSocket("GET HTTP/1.1");
        String expected = "GET HTTP/1.1";

        assertEquals(expected, service.getRequest(fakeSocket));
    }

    @Test
    public void getContentLength() throws Exception {
        SocketService service = new SocketService();
        int number = 37;
        String content = "<html><body> Hello World <body><html>";

        assertEquals(number, service.getContentLength(content));
    }

    @Test
    public void readFile1() throws Exception {
        SocketService service = new SocketService();

        String file1 = "file1 contents";

        assertEquals(file1, service.readFile1());

    }

    @Test
    public void splitGetRequest() throws Exception {
        SocketService service = new SocketService();
        Socket fakeSocket = new FakeSocket("GET / HTTP/1.1");

        String firstElement = "GET";

        assertEquals(firstElement, service.splitGetRequest(fakeSocket)[0]);

    }

    @Test
    public void secondGetRequestElement() throws Exception {
        SocketService service = new SocketService();
        Socket fakeSocket = new FakeSocket("GET /file1 HTTP/1.1");

        String firstElement = "/file1";

        assertEquals(firstElement, service.splitGetRequest(fakeSocket)[1]);

    }

//    @Test
//    public void parseGetRequest() throws Exception {
//        SocketService service = new SocketService();
//        Socket fakeSocket = new FakeSocket("GET / HTTP/1.1");
//
//        String file1Contents = "file 1 Contents";
//
//        assertEquals(file1Contents, service.parseGetRequest(fakeSocket));
//    }

//    @Test
//    public void testThis() throws Exception {
//        SocketService service = new SocketService();
//        boolean boo = true;
//        Socket fakeSocket = new FakeSocket("GET HTTP/1.1");
//
//        assertEquals(true, service.testThis(fakeSocket));
//    }
}