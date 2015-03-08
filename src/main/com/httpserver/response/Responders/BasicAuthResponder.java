package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Responder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BasicAuthResponder implements Responder {
  private String directory;
  private String basicAuthCredentials;
  private String uri;

  public BasicAuthResponder(String directory, String uri, String basicAuthCredentials) {
    this.directory = directory;
    this.basicAuthCredentials = basicAuthCredentials;
    this.uri = uri;
  }
  
  @Override
  public byte[] getHTTPMessageBody() {
    if (isCredentialMatch()) {
      try {
        return Files.readAllBytes(Paths.get(directory + uri));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    try {
      logRequests();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return "Authentication required".getBytes();
  }
  
  @Override
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes) {
    return isCredentialMatch() ? httpStatusCodes.getStatus(200) : httpStatusCodes.getStatus(401);
  }
  
  private boolean isCredentialMatch() {
    for (String credential : credentialsList()) {
      if (basicAuthCredentials.equals(credential)) {
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
  
  private void logRequests() throws FileNotFoundException {
    File file = new File(directory + uri);
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    PrintStream printStream = new PrintStream(fileOutputStream);
    System.setOut(printStream);
  }
}