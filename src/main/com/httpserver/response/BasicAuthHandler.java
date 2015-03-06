package com.httpserver.response;

import com.httpserver.request.Request;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BasicAuthHandler implements Responder {
  private String directory;

  public BasicAuthHandler(String directory) {
    this.directory = directory;
  }
  
  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    if (request.isABasicAuthRequest() && isCredentialMatch(request)) {
      try {
        httpStatusCodes.setStatus(200);
      } catch (Exception e) {
        e.printStackTrace();
      }

      try {
        return Files.readAllBytes(Paths.get(directory + request.getURI()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try {
      httpStatusCodes.setStatus(401);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      logRequests(request);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return "Authentication required".getBytes();
  }
  
  private boolean isCredentialMatch(Request request) {
    for (String credential : credentialsList()) {
      if (request.getBasicAuthCredentials().equals(credential)) {
        return true;
      }
    }
    
    return false;
  }

  private ArrayList<String> credentialsList() {
    ArrayList<String> credentials = new ArrayList<String>();
    credentials.add("byron:woodfork");
    credentials.add("admin:hunter2");

    return credentials;
  }
  
  private void logRequests(Request request) throws FileNotFoundException {
    File file = new File(directory + request.getURI());
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    PrintStream printStream = new PrintStream(fileOutputStream);
    System.setOut(printStream);
  }
}