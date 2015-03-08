package com.httpserver.response;

import com.httpserver.testresources.TestDirectoryPath;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class PatchContentHandlerTest {
  @Test
  public void writesPatchContentToPatchContentFileWhenEtagMatches() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    String uri = "patch-content.txt";
    String etag = "60bb224c68b1ed765a0f84d910de58d0beea91c4";
    PatchContentHandler patchContentHandler = new PatchContentHandler(testDirectoryPath, uri, etag);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    patchContentHandler.buildResponse(httpStatusCodes);
    
    assertEquals("patched content", new String(Files.readAllBytes(Paths.get(testDirectoryPath + "/" + uri).toAbsolutePath())));
    assertEquals("204 No Content", httpStatusCodes.getStatus());
  }
  
  @Test
  public void writesDefaultContentToPatchContentFileWhenEtagMatches() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    String uri = "patch-content.txt";
    String etag = "69bc18dc1edc9e1316348b2eaaca9df83898249fC";
    PatchContentHandler patchContentHandler = new PatchContentHandler(testDirectoryPath, uri, etag);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    patchContentHandler.buildResponse(httpStatusCodes);

    assertEquals("default content", new String(Files.readAllBytes(Paths.get(testDirectoryPath + "/" + uri).toAbsolutePath())));
    assertEquals("204 No Content", httpStatusCodes.getStatus());
  }
  
  @Test
  public void returnsDefaultContentWhenThereIsNoEtag() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    String uri = "patch-content.txt";
    String etag = "SomeTagThatDoesNotExist";
    PatchContentHandler patchContentHandler = new PatchContentHandler(testDirectoryPath, uri, etag);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    patchContentHandler.buildResponse(httpStatusCodes);
    
    assertEquals("default content", new String(Files.readAllBytes(Paths.get(testDirectoryPath + "/" + uri).toAbsolutePath())));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}