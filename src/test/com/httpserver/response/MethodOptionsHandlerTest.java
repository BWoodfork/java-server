package com.httpserver.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodOptionsHandlerTest {
  @Test
  public void containTheHTMLRefreshString() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    MethodOptionsHandler methodOptionsHandler = new MethodOptionsHandler();
    String refreshString = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>";
    assertTrue(new String(methodOptionsHandler.buildResponse(httpStatusCodes)).contains(refreshString));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}