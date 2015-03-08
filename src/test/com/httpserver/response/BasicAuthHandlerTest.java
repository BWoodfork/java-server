package com.httpserver.response;

import com.httpserver.testresources.TestDirectoryPath;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicAuthHandlerTest {
  @Test
  public void returnsAuthenticationRequiredIfRequestDoesNotHaveCredentials() throws Exception {
    String testDirectory = TestDirectoryPath.testDirectory;
    String uri = "logs";
    String basicAuthCredentials = "SomeCredentialsThatAreInvalid";
    BasicAuthHandler basicAuthHandler = new BasicAuthHandler(testDirectory, uri, basicAuthCredentials);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();

    assertEquals("Authentication required", new String(basicAuthHandler.buildResponse(httpStatusCodes)));
    assertEquals("401 Unauthorized", httpStatusCodes.getStatus());
  }
  
  @Test
  public void readsRequestsFromLogFile() throws Exception {
    String testDirectory = TestDirectoryPath.testDirectory;
    String uri = "logs";
    String basicAuthCredentials = "admin:hunter2";
    BasicAuthHandler basicAuthHandler = new BasicAuthHandler(testDirectory, uri, basicAuthCredentials);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    basicAuthHandler.buildResponse(httpStatusCodes);
    
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}