package com.httpserver.response;

import com.httpserver.request.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ResponsePresenter {
  private static final String NEW_LINE = "\r\n";
  private Response response;
  
  public ResponsePresenter(Response response) {
    this.response = response;
  }

  public byte[] formatHTTPStatusMessage(Request request) {
    String message = "HTTP/1.1 " + response.getHTTPStatusMessage(request) + NEW_LINE;
    return message.getBytes();
  }

  public byte[] formatHTTPMessageBody(Request request) {
    return response.getHTTPMessageBody(request);
  }

  public byte[] formatLocationHeader() {
    String location = response.getLocation() + NEW_LINE;
    
    return location.getBytes();
  }

  public byte[] formatContentTypeHeader(Request request) {
    String type = response.getContentType(request) + NEW_LINE;
    
    return type.getBytes();
  }
  
  public byte[] formatAllowHeader(Request request) {
    String allowHeader = response.getAllowHeader(request) + NEW_LINE;
    
    return allowHeader.getBytes();
  }

  public byte[] formatContentLength(Request request) {
    String contentLength = response.getContentLength(request) + NEW_LINE + NEW_LINE;
    
    return contentLength.getBytes();
  }

  public byte[] present(Request request) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte[] body = formatHTTPMessageBody(request);

    byteArrayOutputStream.write(formatHTTPStatusMessage(request));
    byteArrayOutputStream.write(formatLocationHeader());
    byteArrayOutputStream.write(formatContentTypeHeader(request));
    byteArrayOutputStream.write(formatAllowHeader(request));
    byteArrayOutputStream.write(formatContentLength(request));
    byteArrayOutputStream.write(body);

    return byteArrayOutputStream.toByteArray();
  }
}