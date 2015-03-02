import org.junit.Test;

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
  public void returnsEmptyFileWhenAuthRequestIsSuccessful() throws Exception {
    String testDirectory = TestDirectoryPath.testDirectory;
    BasicAuthHandler basicAuthHandler = new BasicAuthHandler(testDirectory);
    Request request = new Request();
    request.setURI("logs");
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setBasicRequestStatus(true);
    request.setBasicAuthCredentials("admin:hunter2");
    
    assertEquals("", new String(basicAuthHandler.buildResponse(request, httpStatusCodes)));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}