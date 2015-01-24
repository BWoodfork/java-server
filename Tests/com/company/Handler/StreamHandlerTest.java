package com.company.Handler;

import com.company.Response.RequestBuilder;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void convertsTheRawRequestToAString() throws Exception {
        RequestBuilder requestBuilder = new RequestBuilder();
        StreamHandler streamHandler = new StreamHandler(requestBuilder);
        requestBuilder = new RequestBuilder();

        FakeSocket fakeSocket = new FakeSocket("GET /logs HTTP/1.1Connection: closeHost: localhost:5000");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fakeSocket.getInputStream()));

        String requestString = requestBuilder.getRequestString(bufferedReader);

        assertEquals(requestString, streamHandler.convertRawRequestToString(fakeSocket));
    }
}