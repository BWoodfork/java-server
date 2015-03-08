package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RootResponderTest {
  @Test
  public void returnsTheIndexPageWhenRootRequestIsMade() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String serverViewsDirectory = TestDirectoryPath.serverViewsDirectory;
    RootResponder rootResponder = new RootResponder(serverViewsDirectory);
    byte[] fileBytes = rootResponder.getHTTPMessageBody();
    
    assertTrue(new String(fileBytes).contains("Welcome to the index page!"));
    assertEquals("200 OK", rootResponder.getHTTPStatusCode(httpStatusCodes));
  }
}