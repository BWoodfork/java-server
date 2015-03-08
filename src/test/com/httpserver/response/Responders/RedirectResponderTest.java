package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectResponderTest {
  @Test
  public void returnsTheHTMLStringForRedirecting() throws Exception {
    RedirectResponder redirectResponder = new RedirectResponder();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String redirectString = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>";
    
    assertEquals(redirectString, new String(redirectResponder.getHTTPMessageBody()));
    
    assertEquals("301 Moved Permanently", redirectResponder.getHTTPStatusCode(httpStatusCodes));
  }
}