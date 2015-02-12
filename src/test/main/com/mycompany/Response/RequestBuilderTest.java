package main.com.mycompany.Response;

import main.com.mycompany.request.RequestBuilder;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

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