package com.httpserver.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentParserTest {
  private PartialContentParser partialContentParser;
  private byte[] data;
  
  @Before
  public void setUp() throws Exception {
    String byteRange = "-6";
    partialContentParser = new PartialContentParser(byteRange);
    data =  "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
  }
  
  @Test
  public void returnsTheMinRangeOfNegativeSixByteRange() throws Exception {
    Assert.assertEquals(70, partialContentParser.getMinRange(null, "6", data.length));
  }
  
  @Test
  public void returnsTheMinRangeOfNegative5ByteRange() throws Exception {
    Assert.assertEquals(71, partialContentParser.getMinRange(null, "5", data.length));
  }

  @Test
  public void returnsTheMinRangePositiveFourByteRange() throws Exception {
    Assert.assertEquals(4, partialContentParser.getMinRange("4", "4", data.length));
  }

  @Test
  public void returnsTheMaxRangePositive76WhenMinMatchIs4() throws Exception {
    Assert.assertEquals(76, partialContentParser.getMaxRange("4", null, data.length));
  }

  @Test
  public void returnsTheMaxRangePositive5WhenMinmatchIsZeroAndMaxMatchIsFour() throws Exception {
    Assert.assertEquals(5, partialContentParser.getMaxRange("0", "4", data.length));
  }

  @Test
  public void returnsPartialContentsBasedOnByteRangeRequest() throws Exception {
    assertEquals("a 206." , new String(partialContentParser.getPartialContent(data)));
  }

  @Test
  public void returnsPartialContentForByteRangeZeroToSixRequest() throws Exception {
    String byteRange = "0-6";
    PartialContentParser partialContentParser = new PartialContentParser(byteRange);

    assertEquals("This is" , new String(partialContentParser.getPartialContent(data)));
  }
}
