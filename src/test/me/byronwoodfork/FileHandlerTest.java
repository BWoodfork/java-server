import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class FileHandlerTest {
  private Request request;
  private FileHandler fileHandler;
  private HTTPStatusCodes httpStatusCodes;
  private String testDirectory;
  
  @Before
  public void setUp() throws Exception {
    request = new Request();
    testDirectory = TestDirectoryPath.testDirectory;
    fileHandler = new FileHandler(testDirectory);
    httpStatusCodes = new HTTPStatusCodes();
  }
  
  @Test
  public void returnsThePathOfTheRequestedURIFile() throws Exception {
    request.setURI("file1");
    byte[] fileBytes = fileHandler.buildResponse(request, httpStatusCodes);
    assertEquals("There is some text in here bro", new String(fileBytes));
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }

  @Test
  public void a() throws Exception {
    request.setURI("SomeURIThatDoesNotExist");
    fileHandler.buildResponse(request, httpStatusCodes);

    assertEquals("File Could Not Be Read", new String(fileHandler.buildResponse(request, httpStatusCodes)));
  }
}