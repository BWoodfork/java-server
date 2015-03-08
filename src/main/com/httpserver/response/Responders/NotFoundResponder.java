package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Responder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NotFoundResponder implements Responder {
  private String serverViewsDirectory;
  
  public NotFoundResponder(String serverViewsDirectory) {
    this.serverViewsDirectory = serverViewsDirectory;
  }

  @Override
  public byte[] getHTTPMessageBody() {
    try {
      return Files.readAllBytes(Paths.get(serverViewsDirectory + "/404.html"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "File Could Not Be Read".getBytes();
  }

  @Override
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes) {
    return httpStatusCodes.getStatus(404);
  }
}