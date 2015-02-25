import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseTest {
  private Request request;
  private HTTPStatusCodes httpStatusCodes;
  private Response response;
  
  @Before
  public void setUp() throws Exception {
    response = new Response();
    request = new Request();
    httpStatusCodes = new HTTPStatusCodes();
  }
  
  @Test
  public void returns200OkStatusWhenSuccessfulGetRequestIsMade() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");
    response.getHTTPMessageBody(request, httpStatusCodes);
    
    assertEquals("HTTP/1.1 200 OK\r\n", new String(response.getHTTPStatusMessage(httpStatusCodes)));
  }

  @Test
  public void returns404NotFoundWhenRequestIsMadeToAPathThatIsNotFound() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("SomeFileThatDoesNotExist");
    response.getHTTPMessageBody(request, httpStatusCodes);

    assertEquals("HTTP/1.1 404 Not Found\r\n", new String(response.getHTTPStatusMessage(httpStatusCodes)));
  }

  @Test
  public void returns405MethodNotAllowedWhenMethodIsNotAllowedForURI() throws Exception {
    request.setHTTPMethod("POST");
    request.setURI("file1");
    response.getHTTPMessageBody(request, httpStatusCodes);
    
    assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", new String(response.getHTTPStatusMessage(httpStatusCodes)));
  }

  @Test
  public void returnsAFilesContentsInTheMessageBodyWhenASuccessfulRequestIsMade() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");

    assertEquals("There is some text in here bro", new String(response.getHTTPMessageBody(request, httpStatusCodes)));
  }
  
  @Test
  public void returnsThe404MessageBodyWhenFileNotFound() throws Exception {
    Response response = new Response();
    request.setHTTPMethod("GET");
    request.setURI("SomeURIThatDoesNotExist");
    
    assertTrue(new String(response.getHTTPMessageBody(request, httpStatusCodes)).contains("404 File Not Found"));
  }
  
  @Test
  public void returnsThe405MessageBodyWhenMethodNotAllowed() throws Exception {
    request.setHTTPMethod("POST");
    request.setURI("file1");
    
    assertTrue(new String(response.getHTTPMessageBody(request, httpStatusCodes)).contains("405 Method Not Allowed"));
  }
  
  @Test
  public void returnsTheContentTypeForThePageThatIsDisplayedAfterRequestIsMade() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");

    assertEquals("Content-Type: application/octet-stream\r\n", new String(response.getContentType(request, httpStatusCodes)));
  }
  
  @Test
  public void returnsTheContentTypeForAJPEGImage() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("image.jpeg");
    
    assertEquals("Content-Type: image/jpeg\r\n", new String(response.getContentType(request, httpStatusCodes)));
  }
  
  @Test
  public void returnsTheByteLengthForFile1() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");

    assertEquals("30\r\n\r\n", new String(response.getContentLength(request, httpStatusCodes)));
  }

  @Test
  public void returnsTheByteLengthForAnImageFile() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("image.png");
    
    assertEquals("11324\r\n\r\n", new String(response.getContentLength(request, httpStatusCodes)));
  }
  
  @Test
  public void returnsTheByteLengthForA404NotFoundPage() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("SomeFileThatDoesNotExist");
    
    assertEquals("122\r\n\r\n", new String(response.getContentLength(request, httpStatusCodes)));
  }
}