package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodOptionsResponderTest {
  @Test
  public void containTheHTMLRefreshString() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    MethodOptionsResponder methodOptionsResponder = new MethodOptionsResponder();
    String refreshString = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>";
    assertTrue(new String(methodOptionsResponder.getHTTPMessageBody()).contains(refreshString));
    
    assertEquals("200 OK", methodOptionsResponder.getHTTPStatusCode(httpStatusCodes));
  }
}