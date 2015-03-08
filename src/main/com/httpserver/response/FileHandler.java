package com.httpserver.response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler implements Responder {
  private String directory;
  private String uri;
  
  public FileHandler(String directory, String uri) {
    this.directory = directory;
    this.uri = uri;
  }

  @Override
  public byte[] buildResponse(HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    try {
      return Files.readAllBytes(getPath());
    } catch (IOException e) {
      
      return "File Could Not Be Read".getBytes();
    }
  }

  private Path getPath() {
    return Paths.get(directory + "/" + uri).toAbsolutePath();
  }
}