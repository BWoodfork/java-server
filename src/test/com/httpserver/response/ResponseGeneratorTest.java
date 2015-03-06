package com.httpserver.response;

import com.httpserver.Routes;
import com.httpserver.mocks.ExecutorServiceMock;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.After;
import org.junit.Before;

import java.util.concurrent.ExecutorService;

public class ResponseGeneratorTest {
  private ExecutorService executorServiceMock;
  private ResponseGenerator responseGenerator;
  
  @Before
  public void setUp() throws Exception {
    executorServiceMock = new ExecutorServiceMock();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String testDirectory = TestDirectoryPath.testDirectory;
    Routes routes = new Routes(testDirectory);
    int port = 5000;
    Response response = new Response(httpStatusCodes, routes, port);
    responseGenerator = new ResponseGenerator(executorServiceMock, response, port);
    responseGenerator.start();
  }
  
  @After
  public void tearDown() throws Exception {
    executorServiceMock.shutdown();
  }
}