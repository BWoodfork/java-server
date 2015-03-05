import mocks.MockSocket;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestHandlerTest {
  private InputStream inputStream;
  private OutputStream byteArrayOutputStream;
  
  @Test
  public void returnsTheInputStreamOfTheSocket() throws Exception {
    inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
    byteArrayOutputStream = new ByteArrayOutputStream();
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);

    assertEquals(inputStream, requestHandler.getInputStream());
  }
  
  @Test
  public void returnsTheOutputStreamOfTheSocket() throws Exception {
    inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
    byteArrayOutputStream = new ByteArrayOutputStream();
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);

    assertEquals(byteArrayOutputStream, requestHandler.getOutputStream());
  }
}