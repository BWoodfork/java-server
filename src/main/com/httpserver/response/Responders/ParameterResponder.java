package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Responder;

public class ParameterResponder implements Responder {
  private String parameterValues;
  
  public ParameterResponder(String parameterValues) {
    this.parameterValues = parameterValues;
  }
  
  @Override
  public byte[] getHTTPMessageBody() {
    return parameterValues.getBytes();
  }

  @Override
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes) {
    return httpStatusCodes.getStatus(200);
  }
}