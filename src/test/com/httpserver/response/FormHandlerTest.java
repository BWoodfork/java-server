package com.httpserver.response;

import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormHandlerTest {
  private HTTPStatusCodes httpStatusCodes;
  private String testDirectory;

  @Before
  public void setUp() {
    testDirectory = TestDirectoryPath.testDirectory;
    httpStatusCodes = new HTTPStatusCodes();
  }
 
  @Test
  public void writesSomeTextToFormFileWhenPostRequestIsMade() throws Exception {
    String uri = "form";
    String httpMethod = "POST";
    FormHandler formHandler = new FormHandler(testDirectory, httpMethod, uri);
    byte[] fileBytes = formHandler.buildResponse(httpStatusCodes);
    
    assertEquals("data=cosby", new String(fileBytes));
    Assert.assertEquals("200 OK", httpStatusCodes.getStatus());
  }
  
  @Test
  public void writesHeathCliffWhenPutRequestIsMade() throws Exception {
    String uri = "form";
    String httpMethod = "PUT";
    FormHandler formHandler = new FormHandler(testDirectory, httpMethod, uri);
    byte[] fileBytes = formHandler.buildResponse(httpStatusCodes);

    assertEquals("data=heathcliff", new String(fileBytes));
    Assert.assertEquals("200 OK", httpStatusCodes.getStatus());
  }

  @Test
  public void deletesAllTextFromFileWhenDeleteRequestIsMade() throws Exception {
    String uri = "form";
    String httpMethod = "DELETE";
    FormHandler formHandler = new FormHandler(testDirectory, httpMethod, uri);
    byte[] fileBytes = formHandler.buildResponse(httpStatusCodes);

    assertEquals("", new String(fileBytes));
    Assert.assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}