import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class BasicAuthHandlerTest {
  @Test
  public void returnsAuthenticationRequiredIfRequestDoesNotHaveCredentials() throws Exception {
    String testDirectory = TestDirectoryPath.testDirectory;
    BasicAuthHandler basicAuthHandler = new BasicAuthHandler(testDirectory);
    Request request = new Request();
    request.setURI("logs");
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setBasicAuthCredentials("SomeTextThatIsNotTheRightCredentials");
    
    assertEquals("Authentication required", new String(basicAuthHandler.buildResponse(request, httpStatusCodes)));
    assertEquals("401 Unauthorized", httpStatusCodes.getStatus());
  }
  
  @Test
  public void readsRequestsFromLogFile() throws Exception {
    String testDirectory = TestDirectoryPath.testDirectory;
    BasicAuthHandler basicAuthHandler = new BasicAuthHandler(testDirectory);
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setHTTPMethod("GET");
    request.setURI("logs");
    request.setBasicRequestStatus(true);
    request.setBasicAuthCredentials("admin:hunter2");
    basicAuthHandler.buildResponse(request, httpStatusCodes);
    
    assertEquals("GET /log HTTP/1.1", new String(Files.readAllBytes(Paths.get(testDirectory + "/" + request.getURI()))));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}