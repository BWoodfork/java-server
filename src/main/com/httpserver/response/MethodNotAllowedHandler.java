package com.httpserver.response;

import com.httpserver.request.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MethodNotAllowedHandler implements Responder {
  private String serverViewsDirectory;
  
  public MethodNotAllowedHandler(String serverViewsDirectory) {
    this.serverViewsDirectory = serverViewsDirectory;
  }

  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(405);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      return Files.readAllBytes(Paths.get(serverViewsDirectory + "/405.html"));
    } catch (IOException e) {
      e.printStackTrace();

      return "File Could Not Be Read".getBytes();
    }
  }
}