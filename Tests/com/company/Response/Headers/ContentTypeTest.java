package com.company.Response.Headers;

import com.company.Reponse.Headers.ContentType;
import com.company.Routes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContentTypeTest {
    private ContentType contentType;
    
    @Before
    public void setUp() throws Exception {
        contentType = new ContentType();
    }
    
    @Test
    public void findsTheContentTypeOfFile1() throws Exception {
        String filePath = Routes.file1Route();
        
        assertEquals("text/html" + "\r\n", contentType.getContentType(filePath));
    }
    
    @Test
    public void findsTheContentTypeOfAJPEGImage() throws Exception {
        String filePath = Routes.jpegRoute();
        
        assertEquals("image/jpeg" + "\r\n", contentType.getContentType(filePath));
    }
    
    @Test
    public void findsTheContentTypeOfAPNGImage() throws Exception {
        String filePath = Routes.pngRoute();
        
        assertEquals("image/png" + "\r\n", contentType.getContentType(filePath));
    }
    
    @Test
    public void findsTheContentTypeOfAForm() throws Exception {
       String filePath = Routes.formRoute();
        
        assertEquals("application/x-www-form-urlencoded" + "\r\n", contentType.getContentType(filePath));
    }
    
    @Test
    public void returnsTheContentTypeHeader() throws Exception {
        String filePath = Routes.file1Route();
        
        String getContentTypeHeader = new String(contentType.getContentTypeHeader(filePath));
        String contentTypeString = "Content-Type: text/html" + "\r\n";
        
        assertEquals(contentTypeString, getContentTypeHeader);
    }
}
