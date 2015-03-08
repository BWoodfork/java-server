package com.httpserver.response;

import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileHandlerTest {
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
    FileHandler fileHandler = new FileHandler(testDirectory, uri);
    byte[] fileBytes = fileHandler.buildResponse(httpStatusCodes);
    assertEquals("file1 contents", new String(fileBytes));
    Assert.assertEquals("200 OK", httpStatusCodes.getStatus());
  }

  @Test
  public void returnsFileCouldNotBeReadWhenURIInvalid() throws Exception {
    String uri = "SomeURIThatDoesNotExist";
    FileHandler fileHandler = new FileHandler(testDirectory, uri);
    fileHandler.buildResponse(httpStatusCodes);

    assertEquals("File Could Not Be Read", new String(fileHandler.buildResponse(httpStatusCodes)));
  }
}