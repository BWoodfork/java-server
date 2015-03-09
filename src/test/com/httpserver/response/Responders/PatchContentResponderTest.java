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
    String etag = "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec";
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
    String etag = "5c36acad75b78b82be6d9cbbd6143ab7e0cc04b0";
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