package com.company;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class HeaderOutputTest {
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
//    public void getTheStream() throws Exception {
//        HeaderOutput headerOutput = new HeaderOutput();
//        StreamHandler streamHandler = new StreamHandler();
//
//        FakeSocket fakeSocket = new FakeSocket("GET /logs HTTP/1.1Connection: closeHost: localhost:5000");
//        String stream = streamHandler.getInputStreamStringValue(fakeSocket);
//        RequestParser requestParser = new RequestParser(stream);
//
//        assertEquals(requestParser,headerOutput.parseRequest(fakeSocket));
//    }
}
