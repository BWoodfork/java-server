import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectHandlerTest {
  @Test
  public void returnsTheHTMLStringForRedirecting() throws Exception {
    RedirectHandler redirectHandler = new RedirectHandler();
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String redirectString = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>";
    
    assertEquals(redirectString, new String(redirectHandler.buildResponse(request, httpStatusCodes)));
    assertEquals("301 Moved Permanently", httpStatusCodes.getStatus());
  }
}