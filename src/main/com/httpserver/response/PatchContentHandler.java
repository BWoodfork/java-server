package com.httpserver.response;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class PatchContentHandler implements Responder {
  private String directory;
  private String uri;
  private String etag;
  private static HashMap<String, byte[]> patchMap = new HashMap<String, byte[]>();
  
  public PatchContentHandler(String directory, String uri, String etag) {
    this.directory = directory;
    this.uri = uri;
    this.etag = etag;
  }

  @Override
  public byte[] buildResponse(HTTPStatusCodes httpStatusCodes) {
    try {
      writeToPatchContent(httpStatusCodes);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "Request Invalid".getBytes();
  }
  
  private Path writeToPatchContent(HTTPStatusCodes httpStatusCodes) throws Exception {
    if (getPatchMap().containsKey(etag)) {
      httpStatusCodes.setStatus(204);
      return Files.write(getPath(), getPatchMap().get(etag));
    }

    httpStatusCodes.setStatus(200);
    return Files.write(getPath(), "default content".getBytes());
  }

  private Path getPath() {
    return Paths.get(directory + "/" + uri).toAbsolutePath();
  }
  
  private HashMap<String, byte[]> getPatchMap() {
    patchMap.put("60bb224c68b1ed765a0f84d910de58d0beea91c4", "patched content".getBytes());
    patchMap.put("69bc18dc1edc9e1316348b2eaaca9df83898249fC", "default content".getBytes());
    
    return patchMap;
  }
}