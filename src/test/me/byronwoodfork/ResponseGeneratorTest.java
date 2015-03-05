import mocks.ExecutorServiceMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;

import static org.junit.Assert.assertEquals;

public class ResponseGeneratorTest {
  private ExecutorService executorServiceMock;
  private ResponseGenerator responseGenerator;
  
  @Before
  public void setUp() throws Exception {
    executorServiceMock = new ExecutorServiceMock();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String testDirectory = TestDirectoryPath.testDirectory;
    Routes routes = new Routes(testDirectory);
    Response response = new Response(httpStatusCodes, routes);
    int port = 0;
    responseGenerator = new ResponseGenerator(executorServiceMock, response, port);
    responseGenerator.start();
  }
  
  @After
  public void tearDown() throws Exception {
    executorServiceMock.shutdown();
  }
}