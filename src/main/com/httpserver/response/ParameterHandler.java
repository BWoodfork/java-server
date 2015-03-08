package com.httpserver.response;

public class ParameterHandler implements Responder {
  private String parameterValues;
  
  public ParameterHandler(String parameterValues) {
    this.parameterValues = parameterValues;
  }
  
  @Override
  public byte[] buildResponse(HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return parameterValues.getBytes();
  }
}