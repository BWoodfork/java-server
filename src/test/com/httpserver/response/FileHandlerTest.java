package com.httpserver.response;

import com.httpserver.request.Request;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileHandlerTest {
  private Request request;
  private FileHandler fileHandler;
  private HTTPStatusCodes httpStatusCodes;
  private String testDirectory;
  
  @Before
  public void setUp() throws Exception {
    request = new Request();
    testDirectory = TestDirectoryPath.testDirectory;
    fileHandler = new FileHandler(testDirectory);
    httpStatusCodes = new HTTPStatusCodes();
  }
  
  @Test
  public void returnsThePathOfTheRequestedURIFile() throws Exception {
    request.setURI("file1");
    byte[] fileBytes = fileHandler.buildResponse(request, httpStatusCodes);
    assertEquals("file1 contents", new String(fileBytes));
    Assert.assertEquals("200 OK", httpStatusCodes.getStatus());
  }

  @Test
  public void a() throws Exception {
    request.setURI("SomeURIThatDoesNotExist");
    fileHandler.buildResponse(request, httpStatusCodes);

    assertEquals("File Could Not Be Read", new String(fileHandler.buildResponse(request, httpStatusCodes)));
  }
}