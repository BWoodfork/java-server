package com.company;

import com.company.Reponse.FileRetriever;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileRouterTest {
    private FileRetriever fileresponse;
    private FileRouter fileRouter;

    @Before
    public void setUp() throws Exception {
        fileRouter = new FileRouter();
        fileresponse = new FileRetriever();
    }

    @Test
    public void itGetsFile1() throws Exception {
        String method = "GET";
        String requestPath = "/file1";
        String data = "localhost:5000";
        String byteCount = "";
        byte[] file = fileresponse.getFile();

        assertEquals(new String(file), new String(fileRouter.routeFiles(method, requestPath, data, byteCount)));
    }

    @Test
    public void itGetsFile2() throws Exception {
        String method = "GET";
        String requestPath = "/file2";
        String data = "localhost:5000";
        String byteCount = "";
        byte[] file = fileresponse.getFile2();

        assertEquals(new String(file), new String(fileRouter.routeFiles(method, requestPath, data, byteCount)));
    }

    @Test
    public void itGetsJPEGImage() throws Exception {
        String method = "GET";
        String requestPath = "/image.jpeg";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] jpeg = fileresponse.getJPEG();
        int jpegImageLength = jpeg.length;

        assertEquals(jpegImageLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
    }

    @Test
    public void itGetsPNGImage() throws Exception {
        String method = "GET";
        String requestPath = "/image.png";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] png = fileresponse.getPNG();
        int pngImageLength = png.length;

        assertEquals(pngImageLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
    }

    @Test
    public void itGetsGIF() throws Exception {
        String method = "GET";
        String requestPath = "/image.gif";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] gif = fileresponse.getGIF();
        int gifImageLength = gif.length;

        assertEquals(gifImageLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
    }

    @Test
    public void itGetsHTMLPage() throws Exception {
        String method = "GET";
        String requestPath = "/";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] html = fileresponse.getHTMLPage();

        assertEquals(new String(html), new String(fileRouter.routeFiles(method, requestPath, data, byteCount)));
    }

    @Test
    public void returnsEncodedURLWhenDecodedValueIsRequested() throws Exception {
        String method = "GET";
        String requestPath = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] body = fileresponse.getDecoded();

        assertEquals(new String(body), new String(fileRouter.routeFiles(method, requestPath, data, byteCount)));
    }
    
//    @Test
//    public void returnsSomethingInThatMap() throws Exception {
//        String requestPath = "/file1";
//        byte[] body = fileresponse.getFile();
//
//        assertEquals(new String(body), new String(fileRouter.testMapFunction(requestPath)));
//    }
//
//    @Test
//    public void returnsSomethingElse() throws Exception {
//        String requestPath = "/";
//        byte[] body = fileresponse.getHTMLPage();
//
//        assertEquals(new String(body), new String(fileRouter.testMapFunction(requestPath)));
//    }

//    @Test
//    public void itGetsThePartialContents() throws Exception {
//        String method = "GET";
//        String requestPath = "/partial_content.txt";
//        String data = "localhost:5000";
//        String byteCount = "";
//
//        byte[] partial = fileresponse.getFirstPartialContent();
//
//        assertEquals(new String(partial), new String(fileRouter.routeFiles(method, requestPath, data, byteCount)));
//    }
}