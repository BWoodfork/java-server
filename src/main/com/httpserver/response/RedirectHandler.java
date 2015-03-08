package com.httpserver.response;

public class RedirectHandler implements Responder {
  @Override
  public byte[] buildResponse(HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(301);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>".getBytes();
  }
}