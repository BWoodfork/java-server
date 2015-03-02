import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RootHandlerTest {
  @Test
  public void returnsTheIndexPageWhenRootRequestIsMade() throws Exception {
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String serverViewsDirectory = "../java-server/default-server-views";
    RootHandler rootHandler = new RootHandler(serverViewsDirectory);
    byte[] fileBytes = rootHandler.buildResponse(request, httpStatusCodes);
    
    assertTrue(new String(fileBytes).contains("Welcome to the index page!"));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}