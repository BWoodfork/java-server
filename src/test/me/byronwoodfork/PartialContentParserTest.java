import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentParserTest {
  private PartialContentParser partialContentParser;
  private byte[] data;
  
  @Before
  public void setUp() throws Exception {
    partialContentParser = new PartialContentParser();
    data =  "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
  }
  
  @Test
  public void returnsTheMinRangeOfNegativeSixByteRange() throws Exception {
    assertEquals(71, partialContentParser.getMinRange(null, "6", data.length));
  }
  
  @Test
  public void returnsTheMinRangeOfNegative5ByteRange() throws Exception {
    assertEquals(72, partialContentParser.getMinRange(null, "5", data.length));
  }

  @Test
  public void returnsTheMinRangePositiveFourByteRange() throws Exception {
    assertEquals(4, partialContentParser.getMinRange("4", "4", data.length));
  }

  @Test
  public void returnsTheMaxRangePositive76WhenMinMatchIs4() throws Exception {
    assertEquals(76, partialContentParser.getMaxRange("4", null, data.length));
  }

  @Test
  public void returnsTheMaxRangePositive5WhenMinmatchIsZeroAndMaxMatchIsFour() throws Exception {
    assertEquals(5, partialContentParser.getMaxRange("0", "4", data.length));
  }

  @Test
  public void returnsPartialContentsBasedOnByteRangeRequest() throws Exception {
    Request request = new Request();
    request.setHTTPMethod("GET");
    request.setURI("partial-content.txt");
    request.setByteRange("-6");

    assertEquals(" 206." , new String(partialContentParser.getPartialContent(request, data)));
  }

  @Test
  public void returnsPartialContentForByteRangeZeroToSixRequest() throws Exception {
    Request request = new Request();
    request.setHTTPMethod("GET");
    request.setURI("partial-content.txt");
    request.setByteRange("0-6");

    assertEquals("This is" , new String(partialContentParser.getPartialContent(request, data)));
  }
}
