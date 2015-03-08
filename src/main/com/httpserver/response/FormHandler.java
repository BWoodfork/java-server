package com.httpserver.response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FormHandler implements Responder {
  private String directory;
  private String uri;
  private String httpMethod;
  private static HashMap<String, byte[]> formMap = new HashMap<String, byte[]>();
  
  public FormHandler(String directory, String httpMethod, String uri) {
    this.directory = directory;
    this.httpMethod = httpMethod;
    this.uri = uri;
  }
  
  @Override
  public byte[] buildResponse(HTTPStatusCodes httpStatusCodes) {
    try {
      writeToForm();
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      return Files.readAllBytes(getPath());
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "File Could Not Be Read".getBytes();
  }

  private Path writeToForm() throws IOException {
    return Files.write(getPath(), getFormMap().get(httpMethod));
  }
  
  private Path getPath() {
    return Paths.get(directory + "/" + uri).toAbsolutePath();
  }
  
  private static HashMap<String, byte[]> getFormMap() {
    formMap.put("POST", "data=cosby".getBytes());
    formMap.put("PUT", "data=heathcliff".getBytes());
    formMap.put("DELETE", "".getBytes());

    return formMap;
  }
}