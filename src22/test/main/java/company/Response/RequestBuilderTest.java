package main.java.company.Response;

import main.java.company.request.RequestBuilder;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class RequestBuilderTest {
    @Test
    public void itBuildsTheRequestWithTheStreamAndReturnsTheRequestString() throws Exception {
        RequestBuilder requestBuilder = new RequestBuilder();

        String request = "GET /logs HTTP/1.1";

        InputStream inputStream = new ByteArrayInputStream(request.getBytes());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String mockRequestString = "GET /logs HTTP/1.1";

        assertEquals(mockRequestString, requestBuilder.getRequestString(bufferedReader));
    }
}