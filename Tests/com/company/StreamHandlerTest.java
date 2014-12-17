package com.company;

import com.company.HeaderData.HeaderOutput;
import com.company.HeaderData.StreamHandler;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

public class StreamHandlerTest {
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
//
//        FakeSocket fakeSocket = new FakeSocket("GET /logs HTTP/1.1Connection: closeHost: localhost:5000");
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fakeSocket.getInputStream()));
//
//
//        assertEquals("",headerOutput.getStream(fakeSocket));
//    }
}