package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentHandlerTest {
    private PartialContentHandler partialContentHandler;

    @Before
    public void setUp() throws Exception {
        partialContentHandler = new PartialContentHandler();
    }

    @Test
    public void getsTheIntegerValueOfAPartialRequest() throws Exception {
        String byteCount = "bytes=0-4Connection:";

        assertEquals("0-4" ,partialContentHandler.parseRequest(byteCount));
    }

    @Test
    public void getsTheNegativeIntegerValueOfAPartialRequest() throws Exception {
        String byteCount = "bytes=-4Connection:";

        assertEquals("-4" ,partialContentHandler.parseRequest(byteCount));
    }

    @Test
    public void returnsTheMinimumRangeOfTheRequestMade() throws Exception {
        String byteCount = "bytes=-6Connection:";

        byte[] fakeFileContent =  "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(71, partialContentHandler.getMinRange(fakeFileContent, byteCount));
    }

    @Test
    public void returnsTheMinimumRangeOfNegative5() throws Exception {
        String byteCount = "bytes=-5Connection:";

        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(72, partialContentHandler.getMinRange(fakeFileContent, byteCount));
    }

    @Test
    public void returnsTheMinimumRangeWhenTwoNumbersAreGiven() throws Exception {
        String byteCount = "bytes=0-4Connection:";

        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();

        assertEquals(0, partialContentHandler.getMinRange(fakeFileContent, byteCount));
    }

    @Test
    public void returnsTheMinimumRangeWhen4() throws Exception {
        String byteCount = "bytes=4-4Connection:";

        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();

        assertEquals(4, partialContentHandler.getMinRange(fakeFileContent, byteCount));
    }

    @Test
    public void returnsTheMaximumRangeOfPositive4() throws Exception {
        String byteCount = "bytes=4-Connection:";

        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();

        assertEquals(fakeFileContent.length, partialContentHandler.getMaxRange(fakeFileContent, byteCount));
    }

    @Test
    public void returnsTheMaximumRangeOfPositive6() throws Exception {
        String byteCount = "bytes=6-Connection:";

        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();

        assertEquals(fakeFileContent.length, partialContentHandler.getMaxRange(fakeFileContent, byteCount));
    }

    @Test
    public void returnsTheMaximumRangeWhenTwoRangesAreGiven() throws Exception {
        String byteCount = "bytes=0-4Connection:";

        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();

        assertEquals(5, partialContentHandler.getMaxRange(fakeFileContent, byteCount));
    }

    @Test
    public void returnsTheMaxiumRangeWhenSecondNumberIs8() throws Exception {
        String byteCount = "bytes=0-8Connection:";

        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();

        assertEquals(9, partialContentHandler.getMaxRange(fakeFileContent, byteCount));
    }

    @Test
    public void returnsTheRangeOfTheFileContentsWhenOneNumberGiven() throws Exception {
        String byteCount = "bytes=-6Connection:";

        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();

        assertEquals(new String(" 206.".getBytes()), new String(partialContentHandler.getPartialContents(fakeFileContent, byteCount)));
    }

    @Test
    public void returnsTheRangeOfTheFileContentsWhenTwoNumbersGiven() throws Exception {
        String byteCount = "bytes=0-4Connection:";

        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();

        assertEquals(new String("This ".getBytes()), new String(partialContentHandler.getPartialContents(fakeFileContent, byteCount)));
    }
}
