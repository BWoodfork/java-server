import Mocks.MockSocket;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class RequestHandlerTest {
  private InputStream inputStream;
  private OutputStream byteArrayOutputStream;
  
  @Test
  public void returnsTheInputStreamOfTheSocket() throws Exception {
    inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
    byteArrayOutputStream = new ByteArrayOutputStream();
    byteArrayOutputStream.write("HTTP/1.1 200 OK".getBytes());

    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);
    SocketWrapper socketWrapper = new SocketWrapper(mockSocket);
    
    assertEquals(socketWrapper.getSocketInputStream(), requestHandler.getInputStream());
  }
  
  @Test
  public void returnsTheOutputStreamOfTheSocket() throws Exception {
    inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
    byteArrayOutputStream = new ByteArrayOutputStream();
    byteArrayOutputStream.write("HTTP/1.1 200 OK".getBytes());
    
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);
    SocketWrapper socketWrapper = new SocketWrapper(mockSocket);
    
    assertEquals(socketWrapper.getSocketOutputStream(), requestHandler.getOutputStream());
  }
  
  @Test
  public void returnsTheStringValueOfTheInputStream() throws Exception {
    inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);
    
    assertEquals("GET / HTTP/1.1", requestHandler.convertRequestToString());
  }
  
  @Test
  public void returnsTheStringValueOfARequestToADifferentURI() throws Exception {
    inputStream = new ByteArrayInputStream("GET /file50000 HTTP/1.1".getBytes());
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);

    assertEquals("GET /file50000 HTTP/1.1", requestHandler.convertRequestToString());
  }
  
  @Test
  public void returnsEntireRequestIfRequestContainsNewlines() throws Exception {
    String request = "GET /file50000 \r\n" + "some other text";
    inputStream = new ByteArrayInputStream(request.getBytes());
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    RequestHandler requestHandler = new RequestHandler(mockSocket);

    assertEquals("GET /file50000 some other text", requestHandler.convertRequestToString());
  }
}