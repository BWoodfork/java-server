import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class FileHandlerTest {
  private Request request;
  private FileHandler fileHandler;
  private HTTPStatusCodes httpStatusCodes;
  private String tempDirectory;
  
  @Before
  public void setUp() throws Exception {
    request = new Request();
    tempDirectory = "../java-server/temp-directory";
    fileHandler = new FileHandler(tempDirectory);
    httpStatusCodes = new HTTPStatusCodes();
  }
  
  @Test
  public void returnsACollectionOfFileNamesWhenGivenADirectory() throws Exception {
    File file = new File(tempDirectory);
    String[] fileList = file.list();
    
    assertThat(Arrays.asList(fileHandler.getDirectoryFileNames()), hasItems(fileList));
  }
  
  @Test(expected = NullPointerException.class)
  public void throwsNullPointerExceptionWhenDirectoryDoesNotExist() throws Exception {
    String directory = "";
    File file = new File(directory);
    String[] fileList = file.list();

    assertThat(Arrays.asList(fileHandler.getDirectoryFileNames()), hasItems(fileList));
  }
  
  @Test
  public void returnsTheURIThatMatchesAURIFromAGivenCollectionOfFileNames() throws Exception {
    String[] fileList = {"file1", "file2", "file3"};
    request.setURI("file1");
    
    assertEquals("file1", fileHandler.matchRequestedFile(fileList, request));
  }
  
  @Test
  public void returnsTheURIForFile2IfItMatchesAURIInACollectionOfFileNames() throws Exception {
    String[] fileList = {"file1", "file2", "file3"};
    request.setURI("file2");

    assertEquals("file2", fileHandler.matchRequestedFile(fileList, request));
  }
  
  @Test
  public void returnsANullPointerExceptionStringIfRequestedURIDoesNotExistInCollection() throws Exception {
    String [] fileList = {"file1", "file2", "file3"};
    request.setURI("SomeFileNameThatDoesNotExistInFileListArray");

    assertEquals(request.getURI(), fileHandler.matchRequestedFile(fileList, request));
  }

  @Test
  public void returnsThePathOfTheRequestedURIFile() throws Exception {
    request.setURI("file1");
    byte[] fileBytes = Files.readAllBytes(fileHandler.buildResponse(request, httpStatusCodes));
    assertEquals("There is some text in here bro", new String(fileBytes));
  }

  @Test
  public void setsHTTPStatusTo200OKWhenFileIsSuccessfullyRetrieved() throws Exception {
    fileHandler.buildResponse(request, httpStatusCodes);
    assertEquals("200 OK", httpStatusCodes.getStatus());
  }
}