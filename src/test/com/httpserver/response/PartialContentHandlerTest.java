package com.httpserver.response;

import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentHandlerTest {
  private PartialContentHandler partialContentHandler;
  private String testDirectory;

  @Before
  public void setUp() throws Exception {
    testDirectory = TestDirectoryPath.testDirectory;
    String byteRange = "-6";
    String uri = "partial_content.txt";
    partialContentHandler = new PartialContentHandler(testDirectory, uri, byteRange);
  }

  @Test
  public void returnsPartialContentsBasedOnByteRangeRequest() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();

    assertEquals(" 206." , new String(partialContentHandler.buildResponse(httpStatusCodes)));
    assertEquals("206 Partial Content", httpStatusCodes.getStatus());
  }
  
  @Test
  public void returnsPartialContentForByteRangeZeroToSixRequest() throws Exception {
    String uri = "partial_content.txt";
    String byteRange = "0-6";
    PartialContentHandler partialContentHandler = new PartialContentHandler(testDirectory, uri, byteRange);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();

    assertEquals("This is" , new String(partialContentHandler.buildResponse(httpStatusCodes)));
    assertEquals("206 Partial Content", httpStatusCodes.getStatus());
  }
}