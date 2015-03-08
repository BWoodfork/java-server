package com.httpserver.response;

import org.junit.Before;
import org.junit.Test;

import java.security.KeyException;

import static org.junit.Assert.assertEquals;

public class HTTPStatusCodesTest {
  private HTTPStatusCodes httpStatusCodes;
  
  @Before
  public void setUp() throws Exception {
    httpStatusCodes = new HTTPStatusCodes();
  }
  
  @Test
  public void returnsAllTheKeyValuePairsInTheStatusCodesMap() throws Exception {
    assertEquals("200 OK", HTTPStatusCodes.getStatusCodesMap().get(200));
    assertEquals("204 No Content", HTTPStatusCodes.getStatusCodesMap().get(204));
    assertEquals("206 Partial Content", HTTPStatusCodes.getStatusCodesMap().get(206));
    assertEquals("300 Multiple Choices", HTTPStatusCodes.getStatusCodesMap().get(300));
    assertEquals("302 Found", HTTPStatusCodes.getStatusCodesMap().get(302));
    assertEquals("304 Not Modified", HTTPStatusCodes.getStatusCodesMap().get(304));
    assertEquals("307 Temporary Redirect", HTTPStatusCodes.getStatusCodesMap().get(307));
    assertEquals("400 Bad Request", HTTPStatusCodes.getStatusCodesMap().get(400));
    assertEquals("401 Unauthorized", HTTPStatusCodes.getStatusCodesMap().get(401));
    assertEquals("403 Forbidden", HTTPStatusCodes.getStatusCodesMap().get(403));
    assertEquals("404 Not Found", HTTPStatusCodes.getStatusCodesMap().get(404));
    assertEquals("410 Gone", HTTPStatusCodes.getStatusCodesMap().get(410));
    assertEquals("500 Internal Server Error", HTTPStatusCodes.getStatusCodesMap().get(500));
    assertEquals("501 Not Implemented", HTTPStatusCodes.getStatusCodesMap().get(501));
    assertEquals("503 Service Unavailable", HTTPStatusCodes.getStatusCodesMap().get(503));
    assertEquals("550 Permission Denied", HTTPStatusCodes.getStatusCodesMap().get(550));
  }
  
  @Test
  public void returnsHTTPStatusMessage200OKAfterStatusHasBeenSet() throws Exception {
    assertEquals("200 OK", httpStatusCodes.getStatus(200));
  }

  @Test
  public void returnsHTTPStatusMessage300MultipleChoicesAfterStatusHasBeenSet() throws Exception {
    assertEquals("300 Multiple Choices", httpStatusCodes.getStatus(300));
  }
  
  @Test(expected = KeyException.class)
  public void throwsAnExceptionIfKeyIsInvalid() throws Exception {
    httpStatusCodes.isAValidKey(40000);
  }
}