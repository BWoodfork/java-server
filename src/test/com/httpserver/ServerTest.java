package com.httpserver;

import com.httpserver.Server;
import com.httpserver.mocks.ExecutorServiceMock;
import com.httpserver.testresources.TestDirectoryPath;
import org.junit.After;
import org.junit.Before;

import java.util.concurrent.ExecutorService;

public class ServerTest {
  private ExecutorService executorServiceMock;
  private Server server;
  
  @Before
  public void setUp() throws Exception {
    executorServiceMock = new ExecutorServiceMock();
    int port = 5000;
    String directory = TestDirectoryPath.testDirectory;
    server = new Server(executorServiceMock, port, directory);
    server.start();
  }
  
  @After
  public void tearDown() throws Exception {
    executorServiceMock.shutdown();
  }
}