package com.httpserver.response;

import com.httpserver.Routes;
import com.httpserver.request.Request;

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
  
  public String getHTTPStatusMessage(Request request) {
    return routes.getHandler(request).getHTTPStatusCode(httpStatusCodes);
  }

  public byte[] getHTTPMessageBody(Request request) {
    return routes.getHandler(request).getHTTPMessageBody();
  }

  public String getLocation() {
    String portNumber = Integer.toString(port);
    return "Location: http://localhost:" + portNumber + "/";
  }
  
  public String getContentType(Request request) {
    String type = URLConnection.guessContentTypeFromName(request.getURI());
    if (type == null) {
      type = "text/html";
    }
    
    return "Content-Type: " + type;
  }
  
  public String getContentLength(Request request) {
    String httpMessageBodySize = Integer.toString(getHTTPMessageBody(request).length);
    return "Content-Length: " + httpMessageBodySize;
  }
  
  public String getAllowHeader(Request request) {
    return "Allow: " + routes.getOptions(request);
  }
}