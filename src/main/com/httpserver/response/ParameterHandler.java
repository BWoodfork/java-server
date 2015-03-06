package com.httpserver.response;

import com.httpserver.request.Request;

public class ParameterHandler implements Responder {
  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return request.getParameterValues().getBytes();
  }
}