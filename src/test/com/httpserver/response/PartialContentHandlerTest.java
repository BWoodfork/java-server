package com.httpserver.response;

import com.httpserver.request.Request;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentHandlerTest {
  private PartialContentHandler partialContentHandler;

  @Before
  public void setUp() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    partialContentHandler = new PartialContentHandler(testDirectoryPath);
  }

  @Test
  public void returnsPartialContentsBasedOnByteRangeRequest() throws Exception {
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setHTTPMethod("GET");
    request.setURI("partial_content.txt");
    request.setByteRange("-6");
    
    assertEquals(" 206." , new String(partialContentHandler.buildResponse(request, httpStatusCodes)));
  }
  
  @Test
  public void returnsPartialContentForByteRangeZeroToSixRequest() throws Exception {
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setHTTPMethod("GET");
    request.setURI("partial_content.txt");
    request.setByteRange("0-6");

    assertEquals("This is" , new String(partialContentHandler.buildResponse(request, httpStatusCodes)));
  }
}