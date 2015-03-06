package com.httpserver.response;

import com.httpserver.request.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler implements Responder {
  private String directory;
  
  public FileHandler(String directory) {
    this.directory = directory;
  }

  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    try {
      return Files.readAllBytes(getPath(request));
    } catch (IOException e) {
      
      return "File Could Not Be Read".getBytes();
    }
  }

  private Path getPath(Request request) {
    return Paths.get(directory + "/" + request.getURI()).toAbsolutePath();
  }
}