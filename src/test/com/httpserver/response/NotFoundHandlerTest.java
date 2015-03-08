package com.httpserver.response;

import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NotFoundHandlerTest {
  @Test
  public void returns404NotFoundAsResponse() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String serverViewsDirectory = TestDirectoryPath.serverViewsDirectory;
    NotFoundHandler notFoundHandler = new NotFoundHandler(serverViewsDirectory);
    byte[] fileBytes = notFoundHandler.buildResponse(httpStatusCodes);
    
    assertTrue(new String(fileBytes).contains("404 File Not Found"));
    Assert.assertEquals("404 Not Found", httpStatusCodes.getStatus());
  }
}