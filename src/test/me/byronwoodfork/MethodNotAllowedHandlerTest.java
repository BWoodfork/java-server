import org.junit.Test;

import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodNotAllowedHandlerTest {
  @Test
  public void returns405MethodNotAllowedWhenMethodNotAllowed() throws Exception {
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    String serverViewsDirectory = "../java-server/default-server-views";
    MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler(serverViewsDirectory);
    byte[] fileBytes = methodNotAllowedHandler.buildResponse(request, httpStatusCodes);

    assertTrue(new String(fileBytes).contains("405 Method Not Allowed"));
    assertEquals("405 Method Not Allowed", httpStatusCodes.getStatus());
  }
}