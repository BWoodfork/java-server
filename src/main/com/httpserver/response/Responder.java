package com.httpserver.response;

public interface Responder {
  public byte[] getHTTPMessageBody();
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes);
}