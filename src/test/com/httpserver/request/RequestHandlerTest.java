package com.httpserver.request;

import com.httpserver.mocks.MockSocket;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class RequestHandlerTest {
  private InputStream inputStream;
  private OutputStream byteArrayOutputStream;
  
  @Test
  public void returnsTheInputStreamOfTheSocket() throws Exception {
    inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
    byteArrayOutputStream = new ByteArrayOutputStream();
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);

    Assert.assertEquals(inputStream, requestHandler.getInputStream());
  }
  
  @Test
  public void returnsTheOutputStreamOfTheSocket() throws Exception {
    inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
    byteArrayOutputStream = new ByteArrayOutputStream();
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);

    Assert.assertEquals(byteArrayOutputStream, requestHandler.getOutputStream());
  }
}