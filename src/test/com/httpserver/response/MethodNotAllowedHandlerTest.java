package com.httpserver.response;

import com.httpserver.request.Request;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MethodNotAllowedHandlerTest {
  @Test
  public void returns405MethodNotAllowedWhenMethodNotAllowed() throws Exception {
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String serverViewsDirectory = TestDirectoryPath.serverViewsDirectory;
    MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler(serverViewsDirectory);
    byte[] fileBytes = methodNotAllowedHandler.buildResponse(request, httpStatusCodes);

    assertTrue(new String(fileBytes).contains("405 Method Not Allowed"));
    Assert.assertEquals("405 Method Not Allowed", httpStatusCodes.getStatus());
  }
}