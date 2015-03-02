import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentHandlerTest {
  private PartialContentHandler partialContentHandler;
  private byte[] fakeFileContent;
  
  @Before
  public void setUp() throws Exception {
    String testDirectoryPath = TestDirectoryPath.testDirectory;
    partialContentHandler = new PartialContentHandler(testDirectoryPath);

    fakeFileContent =  "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
  }
  
  @Test
  public void returnsTheMinRangeOfNegativeSixByteRange() throws Exception {
    assertEquals(71, partialContentHandler.getMinRange(null, "6", fakeFileContent.length));
  }
  
  @Test
  public void returnsTheMinRangeOfNegative5ByteRange() throws Exception {
    assertEquals(72, partialContentHandler.getMinRange(null, "5", fakeFileContent.length));
  }
  
  @Test
  public void returnsTheMinRangePositiveFourByteRange() throws Exception {
    assertEquals(4, partialContentHandler.getMinRange("4", "4", fakeFileContent.length));
  }
  
  @Test
  public void returnsTheMaxRangePositive76WhenMinMatchIs4() throws Exception {
    assertEquals(76, partialContentHandler.getMaxRange("4", null, fakeFileContent.length));
  }

  @Test
  public void returnsTheMaxRangePositive5WhenMinmatchIsZeroAndMaxMatchIsFour() throws Exception {
    assertEquals(5, partialContentHandler.getMaxRange("0", "4", fakeFileContent.length));
  }
  
  @Test
  public void returnsPartialContentsBasedOnByteRangeRequest() throws Exception {
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setHTTPMethod("GET");
    request.setURI("partial-content.txt");
    request.setByteRange("-6");
    
    assertEquals(" 206." , new String(partialContentHandler.buildResponse(request, httpStatusCodes)));
  }
  
  @Test
  public void returnsPartialContentForByteRangeZeroToSixRequest() throws Exception {
    Request request = new Request();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    request.setHTTPMethod("GET");
    request.setURI("partial-content.txt");
    request.setByteRange("0-6");

    assertEquals("This is" , new String(partialContentHandler.buildResponse(request, httpStatusCodes)));
  }
}