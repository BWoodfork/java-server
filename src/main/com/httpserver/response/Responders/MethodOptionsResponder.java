package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Responder;

public class MethodOptionsResponder implements Responder {
  @Override
  public byte[] getHTTPMessageBody() {
    byte[] refreshPage = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>".getBytes();
    return refreshPage;
  }

  @Override
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes) {
    return httpStatusCodes.getStatus(200);
  }
}