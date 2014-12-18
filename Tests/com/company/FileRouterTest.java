package com.company;

import com.company.Reponse.FileResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileRouterTest {
    private FileResponse fileresponse;
    private FileRouter fileRouter;

    @Before
    public void setUp() throws Exception {
        fileRouter = new FileRouter();
        fileresponse = new FileResponse();
    }

    @Test
    public void itGetsFile1() throws Exception {
        String method = "GET";
        String requestPath = "/file1";
        String data = "localhost:5000";
        String byteCount = "";
        byte[] file = fileresponse.getFile();
        int fileLength = file.length;

        assertEquals(fileLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
    }

    @Test
    public void itGetsFile2() throws Exception {
        String method = "GET";
        String requestPath = "/file2";
        String data = "localhost:5000";
        String byteCount = "";
        byte[] file = fileresponse.getFile();
        int fileLength = file.length;

        assertEquals(fileLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
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
        int htmlPageLength = html.length;

        assertEquals(htmlPageLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
    }

    @Test
    public void returnsEncodedURLWhenDecodedValueIsRequested() throws Exception {
        String method = "GET";
        String requestPath = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] body = fileresponse.getDecoded();
        int bodyLength = body.length;

        assertEquals(bodyLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
    }

//    @Test
//    public void itGetsThePartialContents() throws Exception {
//        String path = "GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000\n";
//
//        byte[] partial = fileresponse.getPartialContent();
//        int logsLength = partial.length;
//
//        assertEquals(logsLength, fileRouter.routeFiles(path).length);
//    }
}