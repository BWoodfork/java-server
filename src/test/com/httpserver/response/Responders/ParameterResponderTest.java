package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParameterResponderTest {
  @Test
  public void returnsTheParameterValues() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String parameterValues = "key = value key = value";
    ParameterResponder parameterResponder = new ParameterResponder(parameterValues);
    assertEquals("key = value key = value", new String(parameterResponder.getHTTPMessageBody()));
    
    assertEquals("200 OK", parameterResponder.getHTTPStatusCode(httpStatusCodes));
  }
}