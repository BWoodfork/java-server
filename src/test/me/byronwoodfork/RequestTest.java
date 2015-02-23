import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestTest {
  private Request request;
  
  @Before
  public void setUp() throws Exception {
    request = new Request();
  }
  
  @Test
  public void returnsTheFullRequestAfterItHasBeenSet() throws Exception {
    request.setFullRequest("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000");
    assertEquals("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000", request.getFullRequest());
  }
  
  @Test
  public void returnsTheHTTPMethodAfterItHasBeenSet() throws Exception {
    request.setHTTPMethod("GET");
    assertEquals("GET", request.getHTTPMethod());
  }
  
  @Test
  public void returnsTheURIAfterItHasBeenSet() throws Exception {
    request.setURI("/file5000");
    assertEquals("/file5000", request.getURI());
  }
  
  @Test
  public void returnsTheHeaderFieldAfterItHasBeenSet() throws Exception {
    request.setHeaderField("Range:");
    assertEquals("Range:", request.getHeaderField());
  }
  
  @Test
  public void returnsTheByteRangeAfterItHasBeenSet() throws Exception {
    request.setByteRange("bytes=0-4");
    assertEquals("bytes=0-4", request.getByteRange());
  }
}