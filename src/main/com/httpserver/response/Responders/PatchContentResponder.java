package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Responder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class PatchContentResponder implements Responder {
  private String directory;
  private String uri;
  private String etag;
  private static HashMap<String, byte[]> patchMap = new HashMap<String, byte[]>();
  
  public PatchContentResponder(String directory, String uri, String etag) {
    this.directory = directory;
    this.uri = uri;
    this.etag = etag;
  }

  @Override
  public byte[] getHTTPMessageBody() {
    try {
      writeToPatchContent();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "Request Invalid".getBytes();
  }
  
  @Override
  public String getHTTPStatusCode(HTTPStatusCodes httpStatusCodes) {
    return getPatchMap().containsKey(etag) ? httpStatusCodes.getStatus(204) : httpStatusCodes.getStatus(200);
  }
  
  private Path writeToPatchContent() throws Exception {
    if (getPatchMap().containsKey(etag)) {
      return Files.write(getPath(), getPatchMap().get(etag));
    }

    return Files.write(getPath(), "default content".getBytes());
  }

  private Path getPath() {
    return Paths.get(directory + "/" + uri).toAbsolutePath();
  }
  
  private HashMap<String, byte[]> getPatchMap() {
    patchMap.put("dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec", "patched content".getBytes());
    patchMap.put("5c36acad75b78b82be6d9cbbd6143ab7e0cc04b0", "default content".getBytes());
    
    return patchMap;
  }
}