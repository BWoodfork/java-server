package com.httpserver.response;

public class MethodOptionsHandler implements Responder {
  @Override
  public byte[] buildResponse(HTTPStatusCodes httpStatusCodes) {
    byte[] refreshPage = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>".getBytes();
    
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return refreshPage;
  }
}