package com.httpserver.response;

import com.httpserver.request.Request;
import com.httpserver.testresources.TestDirectoryPath;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class PatchContentHandlerTest {
  @Test
  public void writesPatchContentToPatchContentFileWhenEtagMatches() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    PatchContentHandler patchContentHandler = new PatchContentHandler(testDirectoryPath);
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setHTTPMethod("PATCH");
    request.setURI("patch-content.txt");
    request.setEtag("60bb224c68b1ed765a0f84d910de58d0beea91c4");
    patchContentHandler.buildResponse(request, httpStatusCodes);
    
    assertEquals("patched content", new String(Files.readAllBytes(Paths.get(testDirectoryPath + "/" + request.getURI()).toAbsolutePath())));
    assertEquals("204 No Content", httpStatusCodes.getStatus());
  }
  
  @Test
  public void writesDefaultContentToPatchContentFileWhenEtagMatches() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    PatchContentHandler patchContentHandler = new PatchContentHandler(testDirectoryPath);
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setHTTPMethod("PATCH");
    request.setURI("patch-content.txt");
    request.setEtag("69bc18dc1edc9e1316348b2eaaca9df83898249fC");
    patchContentHandler.buildResponse(request, httpStatusCodes);

    assertEquals("default content", new String(Files.readAllBytes(Paths.get(testDirectoryPath + "/" + request.getURI()).toAbsolutePath())));
    assertEquals("204 No Content", httpStatusCodes.getStatus());
  }
  
  @Test
  public void returnsDefaultContentWhenThereIsNoEtag() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    PatchContentHandler patchContentHandler = new PatchContentHandler(testDirectoryPath);
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setHTTPMethod("PATCH");
    request.setURI("patch-content.txt");
    request.setEtag("SomethingElse");
    patchContentHandler.buildResponse(request, httpStatusCodes);
    
    assertEquals("default content", new String(Files.readAllBytes(Paths.get(testDirectoryPath + "/" + request.getURI()).toAbsolutePath())));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}