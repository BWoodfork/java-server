import mocks.MockSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;

public class SocketWrapperTest {
  private InputStream inputStream;
  private OutputStream byteArrayOutputStream;
  
  @Before
  public void setUp() throws Exception {
    inputStream = new ByteArrayInputStream("GET / HTTP/1.1".getBytes());
    byteArrayOutputStream = new ByteArrayOutputStream();
    byteArrayOutputStream.write("HTTP/1.1 200 OK".getBytes());
  }
  
  @Test
  public void returnsTrueIfSocketHasBeenClosed() throws Exception {
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    SocketWrapper socketWrapper = new SocketWrapper(mockSocket);
    mockSocket.close();
    assertEquals(true, socketWrapper.socketIsClosed());
  }
  
  @Test
  public void returnsFalseIfSocketHasNotBeenClosed() throws Exception {
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    SocketWrapper socketWrapper = new SocketWrapper(mockSocket);
    assertEquals(false, socketWrapper.socketIsClosed());
  }

  @Test
  public void getInputStreamOnASocketReturnsAnInputStream() throws Exception {
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    SocketWrapper socketWrapper = new SocketWrapper(mockSocket);

    assertEquals(inputStream, socketWrapper.getSocketInputStream());
  }
  
  @Test
  public void getOutputStreamOnASocketReturnsAnOutputStream() throws Exception {
    MockSocket mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    SocketWrapper socketWrapper = new SocketWrapper(mockSocket);

    assertEquals(byteArrayOutputStream, socketWrapper.getSocketOutputStream());
  }
}