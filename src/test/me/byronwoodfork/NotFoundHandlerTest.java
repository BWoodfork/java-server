import org.junit.Test;

import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NotFoundHandlerTest {
  @Test
  public void returns404NotFoundAsResponse() throws Exception {
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String serverViewsDirectory = "../java-server/default-server-views";
    NotFoundHandler notFoundHandler = new NotFoundHandler(serverViewsDirectory);
    byte[] fileBytes = notFoundHandler.buildResponse(request, httpStatusCodes);
    
    assertTrue(new String(fileBytes).contains("404 File Not Found"));
    assertEquals("404 Not Found", httpStatusCodes.getStatus());
  }
}