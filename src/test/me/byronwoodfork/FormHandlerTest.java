import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;

import static org.junit.Assert.*;

public class FormHandlerTest {
  private Request request;
  private FormHandler formHandler;
  private HTTPStatusCodes httpStatusCodes;

  @Before
  public void setUp() {
    request = new Request();
    String testDirectory = TestDirectoryPath.testDirectory;
    formHandler = new FormHandler(testDirectory);
    httpStatusCodes = new HTTPStatusCodes();
  }

  @Test
  public void writesSomeTextToFormFileWhenPostRequestIsMade() throws Exception {
    request.setURI("form");
    request.setHTTPMethod("POST");
    byte[] fileBytes = Files.readAllBytes(formHandler.buildResponse(request, httpStatusCodes));
    
    assertEquals("data=cosby", new String(fileBytes));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
  
  @Test
  public void writesHeathCliffWhenPutRequestIsMade() throws Exception {
    request.setURI("form");
    request.setHTTPMethod("PUT");
    byte[] fileBytes = Files.readAllBytes(formHandler.buildResponse(request, httpStatusCodes));

    assertEquals("heathcliff", new String(fileBytes));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }

  @Test
  public void deletesAllTextFromFileWhenDeleteRequestIsMade() throws Exception {
    request.setURI("form");
    request.setHTTPMethod("DELETE");
    byte[] fileBytes = Files.readAllBytes(formHandler.buildResponse(request, httpStatusCodes));

    assertEquals("", new String(fileBytes));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}