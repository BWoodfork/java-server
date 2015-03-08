package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NotFoundResponderTest {
  @Test
  public void returns404NotFoundAsResponse() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String serverViewsDirectory = TestDirectoryPath.serverViewsDirectory;
    NotFoundResponder notFoundResponder = new NotFoundResponder(serverViewsDirectory);
    byte[] fileBytes = notFoundResponder.getHTTPMessageBody();
    
    assertTrue(new String(fileBytes).contains("404 File Not Found"));
    
    assertEquals("404 Not Found", notFoundResponder.getHTTPStatusCode(httpStatusCodes));
  }
}