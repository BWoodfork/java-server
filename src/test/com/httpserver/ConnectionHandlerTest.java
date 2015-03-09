package com.httpserver;

import com.httpserver.mocks.MockSocket;
import com.httpserver.request.Request;
import com.httpserver.request.RequestHandler;
import com.httpserver.request.RequestParser;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class ConnectionHandlerTest {
  @Test
  public void returnsFullRequestString() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    RequestParser requestParser = new RequestParser(inputStream);
    
    Request request = requestParser.parse();
    assertEquals("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000", request.getFullRequest());
  }
  
  
  @Test
  public void returnsTheOutputStreamInTextWhenARequestIsMade() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);
    String testDirectory = TestDirectoryPath.testDirectory;
    int port = 5000;
    ConnectionHandler connectionHandler = new ConnectionHandler(requestHandler, port, testDirectory);

    String response = "HTTP/1.1 200 OK\r\n" + 
        "Location: http://localhost:5000/\r\n" + 
        "Content-Type: text/html\r\n" +
        "Allow: GET\r\n" + "Content-Length: 14\r\n\r\n" + "file1 contents";
    
    connectionHandler.run();
    assertEquals(response, requestHandler.getOutputStream().toString());
  }
  
  @Test
  public void returnsTheOutputStreamForARequestToFile2() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /file2 HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);
    String testDirectory = TestDirectoryPath.testDirectory;
    int port = 5000;
    ConnectionHandler connectionHandler = new ConnectionHandler(requestHandler, port, testDirectory);
    
    String response = "HTTP/1.1 200 OK\r\n" +
        "Location: http://localhost:5000/\r\n" +
        "Content-Type: text/html\r\n" +
        "Allow: GET\r\n" + "Content-Length: 41\r\n\r\n" + "There is also some text in this file2 bro";
    
    connectionHandler.run();
    assertEquals(response, requestHandler.getOutputStream().toString());
  }
}