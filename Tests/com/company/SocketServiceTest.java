package com.company;

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

//    @Test
//    public void getContentLength() throws Exception {
//        SocketService service = new SocketService();
//        int number = 37;
//        String content = "<html><body> Hello World <body><html>";
//
//        assertEquals(number, service.getContentLength(content));
//    }

//    @Test
//    public void makeGetRequest() throws Exception {
//        SocketService service = new SocketService();
//        Socket fakeSocket = new FakeSocket("GET HTTP/1.1");
//
//        assertEquals("GET HTTP/1.1", service.inputStream(fakeSocket).readLine());
//    }
//
//    @Test
//    public void getRequest() throws Exception {
//        SocketService service = new SocketService();
//        Socket fakeSocket = new FakeSocket("GET HTTP/1.1");
//        String expected = "GET HTTP/1.1";
//
//        assertEquals(expected, service.inputStream(fakeSocket).readLine());
//    }

}