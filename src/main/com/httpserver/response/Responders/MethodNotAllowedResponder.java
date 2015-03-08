package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Responder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MethodNotAllowedResponder implements Responder {
  private String serverViewsDirectory;
  
  public MethodNotAllowedResponder(String serverViewsDirectory) {
    this.serverViewsDirectory = serverViewsDirectory;
  }

  @Override
  public byte[] getHTTPMessageBody() {
    try {
      return Files.readAllBytes(Paths.get(serverViewsDirectory + "/405.html"));
    } catch (IOException e) {
      e.printStackTrace();

      return "File Could Not Be Read".getBytes();
    }
  }

  @Override
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes) {
    return httpStatusCodes.getStatus(405);
  }
}