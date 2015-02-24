import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;

import static org.junit.Assert.*;

public class FormHandlerTest {
  private Request request;
  private FormHandler formHandler;
  private HTTPStatusCodes httpStatusCodes;

  @Before
  public void setUp() {
    request = new Request();
    String tempDirectory = "../java-server/temp-directory";
    formHandler = new FormHandler(tempDirectory);
    httpStatusCodes = new HTTPStatusCodes();
  }
  
  @Test
  public void returnsTrueIfRequestIsAPostRequest() throws Exception {
    request.setHTTPMethod("POST");
    assertTrue(formHandler.isAPostRequest(request));
  }
  
  @Test
  public void returnsFalseIfRequestIsNotAPostRequest() throws Exception {
    request.setHTTPMethod("GET");
    assertFalse(formHandler.isAPostRequest(request));
  }
  
  @Test
  public void returnsTrueIfRequestIsAPUTRequest() throws Exception {
    request.setHTTPMethod("PUT");
    assertTrue(formHandler.isAPutRequest(request));
  }
  
  @Test
  public void returnsFalseIfRequestIsNotAPutRequest() throws Exception {
    request.setHTTPMethod("GET");
    assertFalse(formHandler.isAPutRequest(request));
  }
  
  @Test
  public void returnsTrueIfMethodIsADELETERequest() throws Exception {
    request.setHTTPMethod("DELETE");
    assertTrue(formHandler.isADeleteRequest(request));
  }
  
  @Test
  public void returnsFalseIfMethodIsNotADeleteRequest() throws Exception {
    request.setHTTPMethod("GET");
    assertFalse(formHandler.isADeleteRequest(request));
  }
  
  @Test
  public void writesSomeTextToFormFileWhenPostRequestIsMade() throws Exception {
    request.setURI("form");
    request.setHTTPMethod("POST");
    byte[] fileBytes = Files.readAllBytes(formHandler.buildResponse(request, httpStatusCodes));
    
    assertEquals("data=cosby", new String(fileBytes));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
  
  @Test
  public void writesHeathCliffWhenPutRequestIsMade() throws Exception {
    request.setURI("form");
    request.setHTTPMethod("PUT");
    byte[] fileBytes = Files.readAllBytes(formHandler.buildResponse(request, httpStatusCodes));

    assertEquals("heathcliff", new String(fileBytes));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }

  @Test
  public void deletesAllTextFromFileWhenDeleteRequestIsMade() throws Exception {
    request.setURI("form");
    request.setHTTPMethod("DELETE");
    byte[] fileBytes = Files.readAllBytes(formHandler.buildResponse(request, httpStatusCodes));

    assertEquals("", new String(fileBytes));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}