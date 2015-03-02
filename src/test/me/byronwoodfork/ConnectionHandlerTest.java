import mocks.MockSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ConnectionHandlerTest {
  private RequestHandler requestHandler;
  private MockSocket mockSocket;
  private InputStream inputStream;
  private OutputStream byteArrayOutputStream;
  private RequestParser requestParser;
  private ConnectionHandler connectionHandler;
  private HTTPStatusCodes httpStatusCodes;
  private Response response;
  
  @Before
  public void setUp() throws Exception {
    inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    byteArrayOutputStream = new ByteArrayOutputStream();
    mockSocket = new MockSocket(inputStream, byteArrayOutputStream);
    requestHandler = new RequestHandler(mockSocket);
    requestParser = new RequestParser(inputStream);
    httpStatusCodes = new HTTPStatusCodes();
    response = new Response(httpStatusCodes);
    connectionHandler = new ConnectionHandler(requestHandler, response);
  }
  
  @Test
  public void returnsFullRequestString() throws Exception {
    Request request = requestParser.parse();
    assertEquals("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000", request.getFullRequest());
  }
  
  String textoutput = "HTTP/1.1 200 OK\r\n" + "Location: http://localhost:5000/\r\n" + "Content-Type: text/plain\r\n" +
                      "Allow: GET\r\n" + "Content-Length: 14\r\n\r\n" + "file1 contents";
  
  @Test
  public void returnsTheOutputStreamInTextWhenARequestIsMade() throws Exception {
    connectionHandler.run();
    assertEquals(textoutput, requestHandler.getOutputStream().toString());
    assertEquals("HTTP/1.1 200 OK\r\n", new String(response.getHTTPStatusMessage()));
  }
}