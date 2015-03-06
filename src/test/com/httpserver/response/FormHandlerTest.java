package com.httpserver.response;

import com.httpserver.request.Request;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormHandlerTest {
  private Request request;
  private FormHandler formHandler;
  private HTTPStatusCodes httpStatusCodes;

  @Before
  public void setUp() {
    request = new Request();
    String testDirectory = TestDirectoryPath.testDirectory;
    formHandler = new FormHandler(testDirectory);
    httpStatusCodes = new HTTPStatusCodes();
  }
 
  @Test
  public void writesSomeTextToFormFileWhenPostRequestIsMade() throws Exception {
    request.setURI("form");
    request.setHTTPMethod("POST");
    byte[] fileBytes = formHandler.buildResponse(request, httpStatusCodes);
    
    assertEquals("data=cosby", new String(fileBytes));
    Assert.assertEquals("200 OK", httpStatusCodes.getStatus());
  }
  
  @Test
  public void writesHeathCliffWhenPutRequestIsMade() throws Exception {
    request.setURI("form");
    request.setHTTPMethod("PUT");
    byte[] fileBytes = formHandler.buildResponse(request, httpStatusCodes);

    assertEquals("data=heathcliff", new String(fileBytes));
    Assert.assertEquals("200 OK", httpStatusCodes.getStatus());
  }

  @Test
  public void deletesAllTextFromFileWhenDeleteRequestIsMade() throws Exception {
    request.setURI("form");
    request.setHTTPMethod("DELETE");
    byte[] fileBytes = formHandler.buildResponse(request, httpStatusCodes);

    assertEquals("", new String(fileBytes));
    Assert.assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}