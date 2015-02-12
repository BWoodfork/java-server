package main.com.mycompany.Response.Headers;

import main.com.mycompany.request.Request;
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
        String filePath = "/file1";

        assertEquals("text/html" + "\r\n", contentType.parseRequestPath(filePath));
    }

    @Test
    public void findsTheContentTypeOfAJPEGImage() throws Exception {
        String filePath = "/image.jpeg";

        assertEquals("image/jpeg" + "\r\n", contentType.parseRequestPath(filePath));
    }

    @Test
    public void findsTheContentTypeOfAPNGImage() throws Exception {
        String filePath = "/image.png";

        assertEquals("image/png" + "\r\n", contentType.parseRequestPath(filePath));
    }

    @Test
    public void findsTheContentTypeOfAForm() throws Exception {
        String filePath = "/form";

        assertEquals("application/x-www-form-urlencoded" + "\r\n", contentType.parseRequestPath(filePath));
    }

    @Test
    public void returnsTheContentTypeHeader() throws Exception {
        Request request = new Request("GET /file1");

        assertEquals("Content-Type: text/html\r\n", new String(contentType.getContentType(request)));
    }
}