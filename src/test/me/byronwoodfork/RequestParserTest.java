import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
  private Request defaultRequest;

  @Before
  public void setUp() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    defaultRequest = new RequestParser(inputStream).parse();
  }
  
  @Test
  public void returnsTheMethodOfTheHTTPRequest() throws IOException {
    InputStream inputStream = new ByteArrayInputStream("GET /logs HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("GET", request.getHTTPMethod());
  }
  
  @Test
  public void returnsTheOPTIONSMethodOfTheHTTPRequest() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("OPTIONS /method_options HTTP/1.1Connection: closeHost: localhost: 5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("OPTIONS", request.getHTTPMethod());
  }
  
  @Test
  public void returnsTheURIOfTheHTTPRequest() throws Exception {
    assertEquals("file1", defaultRequest.getURI());
  }
  
  @Test
  public void returnsTheURIOfAParameterRequest() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("parameters", request.getURI());
  }
  
  @Test
  public void returnsTheURIOfARootRequest() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET / HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("/", request.getURI());
  }
  
  @Test
  public void returnsTheHeaderFieldForARangeRequest() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("Range:", request.getHeaderField());
  }
  
  @Test
  public void returnsTheHeaderFieldForAuthorizationRequests() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /logs HTTP/1.1Authorization: Basic YWRtaW46aHVudGVyMg==Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("Authorization:", request.getHeaderField());
  }
  
  @Test
  public void returnsTheByteRangeWhenPartialRequestIsMade() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("0-4", request.getByteRange());
  }
  
  @Test
  public void returnsTheByteRangeWhenRangeIsANegativeNumber() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /partial_content.txt HTTP/1.1Range: bytes=-4Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("-4", request.getByteRange());
  }
  
  @Test
  public void returnsTheFullHTTPRequest() throws Exception {
    assertEquals("GET /file1 HTTP/1.1Connection: closeHost: localhost:5000", defaultRequest.getFullRequest());
  }
  
  @Test
  public void returnsTheFullRequestOfAnOPTIONSRequest() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("OPTIONS /method_options HTTP/1.1Connection: closeHost: localhost: 5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("OPTIONS /method_options HTTP/1.1Connection: closeHost: localhost: 5000", request.getFullRequest());
  }
  
  @Test
  public void returnsTrueIfAuthRequestIsBasic() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /logs HTTP/1.1Authorization: Basic YWRtaW46aHVudGVyMg==Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals(true , request.isABasicAuthRequest());
  }
  
  @Test
  public void returnsFalseIfAuthRequestIsNotBasic() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /logs HTTP/1.1Authorization: NotBasic YWRtaW46aHVudGVyMg==Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals(false, request.isABasicAuthRequest());
  }
  
  @Test
  public void returnsTheDecodedBasicAuthCredentials() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /logs HTTP/1.1Authorization: Basic YWRtaW46aHVudGVyMg==Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("admin:hunter2", request.getBasicAuthCredentials());
  }
  
  @Test
  public void returnsTheDecodedBasicAuthCredentialsForAnotherSetOfCredentials() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /logs HTTP/1.1Authorization: Basic am9objpqYWNvYg==Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("john:jacob", request.getBasicAuthCredentials());
  }
  
  @Test
  public void returnsTheEtagForPatchRequests() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("PATCH /patch-content.txt HTTP/1.1Content-Length: 15If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4Connection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("60bb224c68b1ed765a0f84d910de58d0beea91c4", request.getEtag());
  }
  
  @Test
  public void returnsTheEtagForADifferentPatchRequest() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("PATCH /patch-content.txt HTTP/1.1Content-Length: 15If-Match: 69bc18dc1edc9e1316348b2eaaca9df83898249fConnection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("69bc18dc1edc9e1316348b2eaaca9df83898249f", request.getEtag());
  }
  
  @Test
  public void returnsDecodedParameterKeyAndValuePairs() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"? variable_2 = stuff", request.getParameterValues());
  }
  
  @Test
  public void returnsDecodedParameterKeyAndValuePairsForADifferentParameterRequest() throws Exception {
    InputStream inputStream = new ByteArrayInputStream("GET /parameters?parameter=value&also=another HTTP/1.1Connection: closeHost: localhost:5000".getBytes());
    Request request = new RequestParser(inputStream).parse();
    assertEquals("parameter = value also = another", request.getParameterValues());
  }
}