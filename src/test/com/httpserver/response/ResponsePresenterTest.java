package com.httpserver.response;

import com.httpserver.Routes;
import com.httpserver.request.Request;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponsePresenterTest {
  private ResponsePresenter responsePresenter;
  private Request request;
  
  @Before
  public void setUp() throws Exception {
    request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    Routes routes = new Routes(TestDirectoryPath.testDirectory);
    int port = 5000;
    Response response = new Response(httpStatusCodes, routes, port);
    responsePresenter = new ResponsePresenter(response);
    request.setHTTPMethod("GET");
    request.setURI("file1");
    response.getHTTPMessageBody(request);
  }
  
  @Test
  public void formatsHTTPStatusMessageHeader() throws Exception {
    assertEquals("HTTP/1.1 200 OK\r\n", new String(responsePresenter.formatHTTPStatusMessage(request)));
  }
  
  @Test
  public void returnsTheResponseMessageBody() throws Exception {
    assertEquals("file1 contents", new String(responsePresenter.formatHTTPMessageBody(request)));
  }
  
  @Test
  public void formatsLocationHeader() throws Exception {
    assertEquals("Location: http://localhost:5000/\r\n", new String(responsePresenter.formatLocationHeader()));
  }
  
  @Test
  public void formatsContentTypeHeader() throws Exception {
    assertEquals("Content-Type: text/html\r\n", new String(responsePresenter.formatContentTypeHeader(request)));
  }
  
  @Test
  public void formatsAllowHeader() throws Exception {
    assertEquals("Allow: GET\r\n", new String(responsePresenter.formatAllowHeader(request)));
  }
  
  @Test
  public void formatsContentLength() throws Exception {
    assertEquals("Content-Length: 14\r\n\r\n", new String(responsePresenter.formatContentLength(request)));
  }
  
  @Test
  public void presentsTheResponseInAnOutputStream() throws Exception {
    String response = "HTTP/1.1 200 OK\r\n" +
        "Location: http://localhost:5000/\r\n" +
        "Content-Type: text/html\r\n" +
        "Allow: GET\r\n" +
        "Content-Length: 14\r\n" +
        "\r\n" +
        "file1 contents";
    
    assertEquals(response, new String(responsePresenter.present(request)));
  }
  
  @Test
  public void presentsAResponseWhenRequestIsForFile2() throws Exception {
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    Routes routes = new Routes(TestDirectoryPath.testDirectory);
    int port = 5000;
    Response response = new Response(httpStatusCodes, routes, port);
    ResponsePresenter responsePresenter = new ResponsePresenter(response);
    request.setHTTPMethod("GET");
    request.setURI("file2");
    response.getHTTPMessageBody(request);
    
    String responseString = "HTTP/1.1 200 OK\r\n" +
        "Location: http://localhost:5000/\r\n" +
        "Content-Type: text/html\r\n" +
        "Allow: GET\r\n" +
        "Content-Length: 41\r\n" +
        "\r\n" +
        "There is also some text in this file2 bro";
    
    assertEquals(responseString, new String(responsePresenter.present(request)));
  }
}