package com.httpserver.response;

import com.httpserver.Routes;
import com.httpserver.request.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLConnection;

public class Response {
  private Routes routes;
  private HTTPStatusCodes httpStatusCodes;
  private int port;
  
  public Response(HTTPStatusCodes httpStatusCodes, Routes routes, int port) {
    this.routes = routes;
    this.httpStatusCodes = httpStatusCodes;
    this.port = port;
  }
  
  public byte[] getHTTPStatusMessage(Request request) {
    String formattedStatus = "HTTP/1.1 " + routes.getHandler(request).getHTTPStatusCode(httpStatusCodes) + "\r\n";
    return formattedStatus.getBytes();
  }

  public byte[] getHTTPMessageBody(Request request) {
    return routes.getHandler(request).getHTTPMessageBody();
  }

  public byte[] getLocation() {
    String portNumber = Integer.toString(port);
    String location = "Location: http://localhost:" + portNumber + "/" + "\r\n";

    return location.getBytes();
  }
  
  public byte[] getContentType(Request request) {
    String type = URLConnection.guessContentTypeFromName(request.getURI());
    if (type == null) {
      type = "text/html";
    }
    
    String formatted = "Content-Type: " + type + "\r\n";
    return formatted.getBytes();
  }
  
  public byte[] getContentLength(Request request) {
    String httpMessageBodySize = Integer.toString(getHTTPMessageBody(request).length);
    String formattedSizeHeader = "Content-Length: " + httpMessageBodySize + "\r\n\r\n";
    return formattedSizeHeader.getBytes();
  }
  
  public byte[] getAllowHeader(Request request) {
    String allowHeader = "Allow: " + routes.getOptions(request) + "\r\n";
    
    return allowHeader.getBytes();
  }
  
  public byte[] formatResponse(Request request) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte[] body = getHTTPMessageBody(request);

    byteArrayOutputStream.write(getHTTPStatusMessage(request));
    byteArrayOutputStream.write(getLocation());
    byteArrayOutputStream.write(getContentType(request));
    byteArrayOutputStream.write(getAllowHeader(request));
    byteArrayOutputStream.write(getContentLength(request));
    byteArrayOutputStream.write(body);

    return byteArrayOutputStream.toByteArray();
  }
}