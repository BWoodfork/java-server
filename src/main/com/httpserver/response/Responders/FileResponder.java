package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Responder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileResponder implements Responder {
  private String directory;
  private String uri;
  
  public FileResponder(String directory, String uri) {
    this.directory = directory;
    this.uri = uri;
  }

  @Override
  public byte[] getHTTPMessageBody() {
    try {
      return Files.readAllBytes(getPath());
    } catch (IOException e) {
      
      return "File Could Not Be Read".getBytes();
    }
  }
  
  @Override
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes) {
    return httpStatusCodes.getStatus(200);
  }
  
  private Path getPath() {
    return Paths.get(directory + "/" + uri).toAbsolutePath();
  }
}