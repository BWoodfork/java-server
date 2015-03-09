package com.httpserver.response;

import com.httpserver.Routes;
import com.httpserver.request.Request;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseTest {
  private Request request;
  private HTTPStatusCodes httpStatusCodes;
  private Response response;
  private Routes routes;
  
  @Before
  public void setUp() throws Exception {
    httpStatusCodes = new HTTPStatusCodes();
    String testDirectory = TestDirectoryPath.testDirectory;
    routes = new Routes(testDirectory);
    int port = 5000;
    response = new Response(httpStatusCodes, routes, port);
    request = new Request();
  }
  
  @Test
  public void returns200OkStatusWhenSuccessfulGetRequestIsMade() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");
    response.getHTTPMessageBody(request);
    
    assertEquals("200 OK", response.getHTTPStatusMessage(request));
  }

  @Test
  public void returns404NotFoundWhenRequestIsMadeToAPathThatIsNotFound() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("SomeFileThatDoesNotExist");
    response.getHTTPMessageBody(request);

    assertEquals("404 Not Found", response.getHTTPStatusMessage(request));
  }

  @Test
  public void returns405MethodNotAllowedWhenMethodIsNotAllowedForURI() throws Exception {
    request.setHTTPMethod("POST");
    request.setURI("file1");
    response.getHTTPMessageBody(request);
    
    assertEquals("405 Method Not Allowed", response.getHTTPStatusMessage(request));
  }

  @Test
  public void returnsAFilesContentsInTheMessageBodyWhenASuccessfulRequestIsMade() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");

    assertEquals("file1 contents", new String(response.getHTTPMessageBody(request)));
  }
  
  @Test
  public void returnsThe404MessageBodyWhenFileNotFound() throws Exception {
    int port = 5000;
    Response response = new Response(httpStatusCodes, routes, port);
    request.setHTTPMethod("GET");
    request.setURI("SomeURIThatDoesNotExist");
    
    assertTrue(new String(response.getHTTPMessageBody(request)).contains("404 File Not Found"));
  }
  
  @Test
  public void returnsThe405MessageBodyWhenMethodNotAllowed() throws Exception {
    request.setHTTPMethod("POST");
    request.setURI("file1");
    
    assertTrue(new String(response.getHTTPMessageBody(request)).contains("405 Method Not Allowed"));
  }
  
  @Test
  public void returnsTheServerLocation() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");

    assertEquals("Location: http://localhost:5000/", response.getLocation());
  }
  
  @Test
  public void returnsTheContentTypeForThePageThatIsDisplayedAfterRequestIsMade() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");

    assertEquals("Content-Type: text/html", response.getContentType(request));
  }
  
  @Test
  public void returnsTheContentTypeForAJPEGImage() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("image.jpg");
    
    assertEquals("Content-Type: image/jpeg", response.getContentType(request));
  }

  @Test
  public void returnsTheByteLengthForFile1() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");

    assertEquals("Content-Length: 14", response.getContentLength(request));
  }

  @Test
  public void returnsTheByteLengthForAnImageFile() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("image.png");
    
    assertEquals("Content-Length: 11324", response.getContentLength(request));
  }
  
  @Test
  public void returnsTheByteLengthForA404NotFoundPage() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("SomeFileThatDoesNotExist");
    
    assertEquals("Content-Length: 122", response.getContentLength(request));
  }
  
  @Test
  public void returnsTheFormattedAllowHeader() throws Exception {
    request.setURI("file1");
    
    assertEquals("Allow: GET", response.getAllowHeader(request));
  }
  
  @Test
  public void returnsTheFormattedAllowHeaderForFormRoute() throws Exception {
    request.setURI("form");
    
    assertEquals("Allow: DELETE,POST,GET,PUT", response.getAllowHeader(request));
  }
  
  @Test
  public void returnsAllowGETForCobspecRoutes() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("log");

    assertEquals("Allow: GET", response.getAllowHeader(request));
  }
}