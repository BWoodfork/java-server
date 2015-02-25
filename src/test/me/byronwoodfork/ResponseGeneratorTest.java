import mocks.ExecutorServiceMock;
import mocks.MockSocket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.assertEquals;

public class ResponseGeneratorTest {
  private ResponseGenerator responseGenerator;
  private ExecutorService executorService;
  private RequestHandler requestHandler;
  private MockSocket mockSocket;
  private InputStream inputStream;
  private OutputStream byteArrayOutputStream;
  private RequestParser requestParser;

  @Before
  public void setUp() throws Exception {
    inputStream = new ByteArrayInputStream("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000".getBytes());
    byteArrayOutputStream = new ByteArrayOutputStream();
    byteArrayOutputStream.write("HTTP/1.1 200 OK".getBytes());
    mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    requestHandler = new RequestHandler(mockSocket);
    executorService = new ExecutorServiceMock();
    responseGenerator = new ResponseGenerator(executorService, requestHandler);
    responseGenerator.start();
    requestParser = new RequestParser(inputStream);
  }
  
  @After
  public void tearDown() throws Exception {
    responseGenerator.stop();
    executorService.shutdown();
  }
  
  @Test
  public void incrementsTheTheNumberOfRequestsMadeToRunnable() throws Exception {
    responseGenerator.run();
    assertEquals(1, responseGenerator.getRequests());
  }
  
  @Test
  public void RequestReturnsTheFullRequestAfterTheServerStartsUp() throws Exception {
    Request request = requestParser.parse();
    responseGenerator.run();
    assertEquals("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000", request.getFullRequest());
  }
}