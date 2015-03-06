package com.httpserver.response;

import com.httpserver.request.Request;

public class MethodOptionsHandler implements Responder {
  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    byte[] refreshPage = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>".getBytes();
    
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return refreshPage;
  }
}