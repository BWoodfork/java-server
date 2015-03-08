package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormResponderTest {
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
    FormResponder formResponder = new FormResponder(testDirectory, httpMethod, uri);
    byte[] fileBytes = formResponder.getHTTPMessageBody();
    
    assertEquals("data=cosby", new String(fileBytes));
    
    assertEquals("200 OK", formResponder.getHTTPStatusCode(httpStatusCodes));
  }
  
  @Test
  public void writesHeathCliffWhenPutRequestIsMade() throws Exception {
    String uri = "form";
    String httpMethod = "PUT";
    FormResponder formResponder = new FormResponder(testDirectory, httpMethod, uri);
    byte[] fileBytes = formResponder.getHTTPMessageBody();

    assertEquals("data=heathcliff", new String(fileBytes));
    
    assertEquals("200 OK", formResponder.getHTTPStatusCode(httpStatusCodes));
  }

  @Test
  public void deletesAllTextFromFileWhenDeleteRequestIsMade() throws Exception {
    String uri = "form";
    String httpMethod = "DELETE";
    FormResponder formResponder = new FormResponder(testDirectory, httpMethod, uri);
    byte[] fileBytes = formResponder.getHTTPMessageBody();

    assertEquals("", new String(fileBytes));
    
    assertEquals("200 OK", formResponder.getHTTPStatusCode(httpStatusCodes));
  }
}