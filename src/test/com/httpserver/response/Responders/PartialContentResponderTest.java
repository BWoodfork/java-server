package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentResponderTest {
  private PartialContentResponder partialContentResponder;
  private String testDirectory;

  @Before
  public void setUp() throws Exception {
    testDirectory = TestDirectoryPath.testDirectory;
    String byteRange = "-6";
    String uri = "partial_content.txt";
    partialContentResponder = new PartialContentResponder(testDirectory, uri, byteRange);
  }

  @Test
  public void returnsPartialContentsBasedOnByteRangeRequest() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    assertEquals(" 206.\n" , new String(partialContentResponder.getHTTPMessageBody()));
    assertEquals("206 Partial Content", partialContentResponder.getHTTPStatusCode(httpStatusCodes));
  }
  
  @Test
  public void returnsPartialContentForByteRangeZeroToSixRequest() throws Exception {
    String uri = "partial_content.txt";
    String byteRange = "0-6";
    PartialContentResponder partialContentResponder = new PartialContentResponder(testDirectory, uri, byteRange);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();

    assertEquals("This is" , new String(partialContentResponder.getHTTPMessageBody()));
    assertEquals("206 Partial Content", partialContentResponder.getHTTPStatusCode(httpStatusCodes));
  }
}