package com.httpserver.response.Responders;

import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileResponderTest {
  private HTTPStatusCodes httpStatusCodes;
  private String testDirectory;
  
  @Before
  public void setUp() throws Exception {
    testDirectory = TestDirectoryPath.testDirectory;
    httpStatusCodes = new HTTPStatusCodes();
  }
  
  @Test
  public void returnsThePathOfTheRequestedURIFile() throws Exception {
    String uri = "file1";
    FileResponder fileResponder = new FileResponder(testDirectory, uri);
    byte[] fileBytes = fileResponder.getHTTPMessageBody();
    assertEquals("file1 contents", new String(fileBytes));
    
    assertEquals("200 OK", fileResponder.getHTTPStatusCode(httpStatusCodes));
  }

  @Test
  public void returnsFileCouldNotBeReadWhenURIInvalid() throws Exception {
    String uri = "SomeURIThatDoesNotExist";
    FileResponder fileResponder = new FileResponder(testDirectory, uri);
    fileResponder.getHTTPMessageBody();

    assertEquals("File Could Not Be Read", new String(fileResponder.getHTTPMessageBody()));
  }
}