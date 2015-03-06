package com.httpserver.response;
import com.httpserver.request.Request;

public interface Responder {
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes);
}