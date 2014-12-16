package com.company;

import com.company.HeaderData.ContentType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContentTypeTest {
    @Test
    public void getTheJPEGContentType() throws Exception {
        ContentType contentType = new ContentType();

        String filePath = "/image.jpeg";

        assertEquals("image/jpeg\r\n", contentType.getContentType(filePath));
    }

    @Test
    public void getsThePNGContentType() throws Exception {
        ContentType contentType = new ContentType();

        String filePath = "/image.png";

        assertEquals("image/png\r\n", contentType.getContentType(filePath));
    }

    @Test
    public void getsTheGifContentType() throws Exception {
        ContentType contentType = new ContentType();

        String filePath = "/image.gif";

        assertEquals("image/gif\r\n", contentType.getContentType(filePath));
    }

    @Test
    public void getsTheHTMLContentTypeForAllOtherRequests() throws Exception {
        ContentType contentType = new ContentType();

        String filePath = "/";

        assertEquals("text/html\r\n", contentType.getContentType(filePath));
    }
}
