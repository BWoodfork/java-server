package com.company.Handler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartialContentHandlerTest {
    @Test
    public void getsTheIntegerValueOfAPartialRequest() throws Exception {
        String byteCount = "bytes=0-4Connection:";
        assertEquals("0-4", PartialContentHandler.parseRequest(byteCount));
    }

    @Test
    public void getsTheNegativeIntegerValueOfAPartialRequest() throws Exception {
        String byteCount = "bytes=-4Connection:";
        assertEquals("-4", PartialContentHandler.parseRequest(byteCount));
    }

    @Test
    public void returnsTheMinimumRangeOfTheRequestMade() throws Exception {
        byte[] fakeFileContent =  "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(71, PartialContentHandler.getMinRange(null, "6", fakeFileContent.length));
    }

    @Test
    public void returnsTheMinimumRangeOfNegative5() throws Exception {
        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(72, PartialContentHandler.getMinRange(null, "5", fakeFileContent.length));
    }

    @Test
    public void returnsTheMinimumRangeWhenTwoNumbersAreGiven() throws Exception {
        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(0, PartialContentHandler.getMinRange("0", "4", fakeFileContent.length));
    }

    @Test
    public void returnsTheMinimumRangeWhen4() throws Exception {
        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(4, PartialContentHandler.getMinRange("4", "4", fakeFileContent.length));
    }

    @Test
    public void returnsTheMaximumRangeOfPositive4() throws Exception {
        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(fakeFileContent.length, PartialContentHandler.getMaxRange("4", null, fakeFileContent.length));
    }

    @Test
    public void returnsTheMaximumRangeOfPositive6() throws Exception {
        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(fakeFileContent.length, PartialContentHandler.getMaxRange("6", null, fakeFileContent.length));
    }

    @Test
    public void returnsTheMaximumRangeWhenTwoRangesAreGiven() throws Exception {
        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(5, PartialContentHandler.getMaxRange("0", "4", fakeFileContent.length));
    }

    @Test
    public void returnsTheMaxiumRangeWhenSecondNumberIs8() throws Exception {
        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
        assertEquals(9, PartialContentHandler.getMaxRange("0", "8", fakeFileContent.length));
    }

//    @Test
//    public void throwsErrorIfFilePathIsNotAPartialContentRequest() throws Exception {
//        String byteCount = "bytes=-6Connection:";
//        String filePath = "/random-file.txt";
//
//        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
//        assertEquals(new String("The page you are looking for cannot be found.".getBytes()), new String(PartialContentHandler.getPartialContents(fakeFileContent, byteCount, filePath)));
//    }

//    @Test
//    public void returnsTheRangeOfTheFileContentsWhenOneNumberGiven() throws Exception {
//        String byteCount = "bytes=-6Connection:";
//        String filePath = "/partial_content.txt";
//
//        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
//        assertEquals(new String(" 206.".getBytes()), new String(PartialContentHandler.getPartialContents(fakeFileContent, byteCount, filePath)));
//    }
//
//    @Test
//    public void returnsTheRangeOfTheFileContentsWhenTwoNumbersGiven() throws Exception {
//        String byteCount = "bytes=0-4Connection:";
//        String filePath = "/partial_content.txt";
//
//        byte[] fakeFileContent = "This is a file that contains text to read part of in order to fulfill a 206.".getBytes();
//        assertEquals(new String("This ".getBytes()), new String(PartialContentHandler.getPartialContents(fakeFileContent, byteCount, filePath)));
//    }
}
