package com.httpserver.response;

public interface Responder {
  public byte[] buildResponse(HTTPStatusCodes httpStatusCodes);
//  public byte[] getHTTPMessageBody();
//  public void getHTTPStatus(int status);
//  getHTTPStatus can have getStatus() from httpstatuscodes and the Response can call .getHTTPStatus on the responder that gets returned.
}