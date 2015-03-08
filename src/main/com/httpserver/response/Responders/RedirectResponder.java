package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Responder;

public class RedirectResponder implements Responder {
  @Override
  public byte[] getHTTPMessageBody() {
    return "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>".getBytes();
  }

  @Override
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes) {
    return httpStatusCodes.getStatus(301);
  }
}