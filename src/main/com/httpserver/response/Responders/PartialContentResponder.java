package com.httpserver.response.Responders;

import com.httpserver.request.PartialContentParser;
import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Responder;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PartialContentResponder implements Responder {
  private String directory;
  private String uri;
  private String byteRange;

  public PartialContentResponder(String directory, String uri, String byteRange) {
    this.directory = directory;
    this.uri = uri;
    this.byteRange = byteRange;
  }
  
  @Override
  public byte[] getHTTPMessageBody() {
    PartialContentParser partialContentParser = new PartialContentParser(byteRange);
    try {
      return partialContentParser.getPartialContent(Files.readAllBytes(Paths.get(directory + "/" + uri)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "Request Invalid".getBytes();
  }

  @Override
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes) {
    return httpStatusCodes.getStatus(206);
  }
}