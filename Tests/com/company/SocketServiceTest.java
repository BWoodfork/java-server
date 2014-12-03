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

//    @Test
//    public void getContentLength() throws Exception {
//        SocketService service = new SocketService();
//        int number = 37;
//        String content = "<html><body> Hello World <body><html>";
//
//        assertEquals(number, service.getContentLength(content));
//    }

}