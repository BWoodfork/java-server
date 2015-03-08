package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.testresources.TestDirectoryPath;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicAuthResponderTest {
  @Test
  public void returnsAuthenticationRequiredIfRequestDoesNotHaveCredentials() throws Exception {
    String testDirectory = TestDirectoryPath.testDirectory;
    String uri = "logs";
    String basicAuthCredentials = "SomeCredentialsThatAreInvalid";
    BasicAuthResponder basicAuthResponder = new BasicAuthResponder(testDirectory, uri, basicAuthCredentials);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();

    assertEquals("Authentication required", new String(basicAuthResponder.getHTTPMessageBody()));
    
    assertEquals("401 Unauthorized", basicAuthResponder.getHTTPStatusCode(httpStatusCodes));
  }
  
  @Test
  public void readsRequestsFromLogFile() throws Exception {
    String testDirectory = TestDirectoryPath.testDirectory;
    String uri = "logs";
    String basicAuthCredentials = "admin:hunter2";
    BasicAuthResponder basicAuthResponder = new BasicAuthResponder(testDirectory, uri, basicAuthCredentials);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    basicAuthResponder.getHTTPMessageBody();
    
    assertEquals("200 OK", basicAuthResponder.getHTTPStatusCode(httpStatusCodes));
  }
}