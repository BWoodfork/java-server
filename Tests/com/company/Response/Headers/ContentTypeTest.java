package com.company.Response.Headers;

import org.junit.Test;

import com.company.RouteStrings;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class ContentTypeTest {
    private ContentType contentType;

    @Before
    public void setUp() throws Exception {
        contentType = new ContentType();
    }

    @Test
    public void findsTheContentTypeOfFile1() throws Exception {
        String filePath = RouteStrings.file1Route();

        assertEquals("text/html" + "\r\n", contentType.getContentType(filePath));
    }

    @Test
    public void findsTheContentTypeOfAJPEGImage() throws Exception {
        String filePath = RouteStrings.jpegRoute();

        assertEquals("image/jpeg" + "\r\n", contentType.getContentType(filePath));
    }

    @Test
    public void findsTheContentTypeOfAPNGImage() throws Exception {
        String filePath = RouteStrings.pngRoute();

        assertEquals("image/png" + "\r\n", contentType.getContentType(filePath));
    }

    @Test
    public void findsTheContentTypeOfAForm() throws Exception {
        String filePath = RouteStrings.formRoute();

        assertEquals("application/x-www-form-urlencoded" + "\r\n", contentType.getContentType(filePath));
    }

    @Test
    public void returnsTheContentTypeHeader() throws Exception {
        String filePath = RouteStrings.file1Route();

        String getContentTypeHeader = new String(contentType.getContentTypeHeader(filePath));
        String contentTypeString = "Content-Type: text/html" + "\r\n";

        assertEquals(contentTypeString, getContentTypeHeader);
    }
}
