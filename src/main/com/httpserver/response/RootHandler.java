package com.httpserver.response;

import com.httpserver.request.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RootHandler implements Responder {
  private String serverViewsDirectory;
  
  public RootHandler(String serverViewsDirectory) {
    this.serverViewsDirectory = serverViewsDirectory;
  }

  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      return Files.readAllBytes(Paths.get(serverViewsDirectory + "/index.html"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "File Could Not Be Read".getBytes();
  }
}