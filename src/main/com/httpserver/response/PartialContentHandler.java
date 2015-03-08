package com.httpserver.response;

import com.httpserver.request.PartialContentParser;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PartialContentHandler implements Responder {
  private String directory;
  private String uri;
  private String byteRange;

  public PartialContentHandler(String directory, String uri, String byteRange) {
    this.directory = directory;
    this.uri = uri;
    this.byteRange = byteRange;
  }
  
  @Override
  public byte[] buildResponse(HTTPStatusCodes httpStatusCodes) {
    PartialContentParser partialContentParser = new PartialContentParser(byteRange);
    
    try {
      httpStatusCodes.setStatus(206);
      return partialContentParser.getPartialContent(Files.readAllBytes(Paths.get(directory + "/" + uri)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "Request Invalid".getBytes();
  }
}