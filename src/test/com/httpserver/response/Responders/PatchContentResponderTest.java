package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.testresources.TestDirectoryPath;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class PatchContentResponderTest {
  @Test
  public void writesPatchContentToPatchContentFileWhenEtagMatches() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    String uri = "patch-content.txt";
    String etag = "60bb224c68b1ed765a0f84d910de58d0beea91c4";
    PatchContentResponder patchContentResponder = new PatchContentResponder(testDirectoryPath, uri, etag);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    patchContentResponder.getHTTPMessageBody();
    
    assertEquals("patched content", new String(Files.readAllBytes(Paths.get(testDirectoryPath + "/" + uri).toAbsolutePath())));
    
    assertEquals("204 No Content", patchContentResponder.getHTTPStatusCode(httpStatusCodes));
  }
  
  @Test
  public void writesDefaultContentToPatchContentFileWhenEtagMatches() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    String uri = "patch-content.txt";
    String etag = "69bc18dc1edc9e1316348b2eaaca9df83898249fC";
    PatchContentResponder patchContentResponder = new PatchContentResponder(testDirectoryPath, uri, etag);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    patchContentResponder.getHTTPMessageBody();

    assertEquals("default content", new String(Files.readAllBytes(Paths.get(testDirectoryPath + "/" + uri).toAbsolutePath())));
    
    assertEquals("204 No Content", patchContentResponder.getHTTPStatusCode(httpStatusCodes));
  }
  
  @Test
  public void returnsDefaultContentWhenThereIsNoEtag() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    String uri = "patch-content.txt";
    String etag = "SomeTagThatDoesNotExist";
    PatchContentResponder patchContentResponder = new PatchContentResponder(testDirectoryPath, uri, etag);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    patchContentResponder.getHTTPMessageBody();
    
    assertEquals("default content", new String(Files.readAllBytes(Paths.get(testDirectoryPath + "/" + uri).toAbsolutePath())));
    
    assertEquals("200 OK", patchContentResponder.getHTTPStatusCode(httpStatusCodes));
  }
}