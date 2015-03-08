package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodNotAllowedResponderTest {
  @Test
  public void returns405MethodNotAllowedWhenMethodNotAllowed() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String serverViewsDirectory = TestDirectoryPath.serverViewsDirectory;
    MethodNotAllowedResponder methodNotAllowedResponder = new MethodNotAllowedResponder(serverViewsDirectory);
    byte[] fileBytes = methodNotAllowedResponder.getHTTPMessageBody();

    assertTrue(new String(fileBytes).contains("405 Method Not Allowed"));
    
    assertEquals("405 Method Not Allowed", methodNotAllowedResponder.getHTTPStatusCode(httpStatusCodes));
  }
}