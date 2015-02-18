import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPStatusCodesTest {
  private HTTPStatusCodes httpStatusCodes;
  
  @Before
  public void setUp() throws Exception {
    httpStatusCodes = new HTTPStatusCodes();
  }
  
  @Test
  public void returnsHTTPStatusMessage200OKAfterStatusHasBeenSet() throws Exception {
    httpStatusCodes.setStatus(200);
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
  
  @Test
  public void returnsHTTPStatusMessage300MultipleChoicesAfterStatusHasBeenSet() throws Exception {
    httpStatusCodes.setStatus(300);
    assertEquals("300 Multiple Choices", httpStatusCodes.getStatus());
  }
  
  @Test(expected = Exception.class)
  public void throwsAnExceptionIfKeyIsInvalid() throws Exception {
    httpStatusCodes.setStatus(400000);
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
  
  @Test
  public void returnsTheHTTPStatusMessageProperlyFormatted() throws Exception {
    httpStatusCodes.setStatus(200);
    assertEquals("HTTP/1.1 200 OK\r\n", httpStatusCodes.getFormattedStatusMessage());
  }
  
  @Test
  public void returnsTheHTTPStatusMessageProperlyFormattedForA404Status() throws Exception {
    httpStatusCodes.setStatus(404);
    assertEquals("HTTP/1.1 404 Not Found\r\n", httpStatusCodes.getFormattedStatusMessage());
  }
}