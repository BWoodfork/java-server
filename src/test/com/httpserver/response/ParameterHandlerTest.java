package com.httpserver.response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParameterHandlerTest {
  @Test
  public void returnsTheParameterValues() throws Exception {
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String parameterValues = "key = value key = value";
    ParameterHandler parameterHandler = new ParameterHandler(parameterValues);
    assertEquals("key = value key = value", new String(parameterHandler.buildResponse(httpStatusCodes)));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}