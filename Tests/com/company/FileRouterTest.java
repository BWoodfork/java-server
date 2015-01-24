package com.company;

import com.company.Handler.PatchRequestHandler;
import com.company.Handler.PostRequestHandler;
import com.company.Response.FileRetriever;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileRouterTest {
    private FileRetriever fileRetriever;
    private FileRouter fileRouter;

    @Before
    public void setUp() throws Exception {
        fileRetriever = new FileRetriever();
        PostRequestHandler postRequestHandler = new PostRequestHandler(fileRetriever);
        PatchRequestHandler patchReqdduestHandler = new PatchRequestHandler(fileRetriever);
        fileRouter = new FileRouter(postRequestHandler, patchReqdduestHandler);
    }    

    @Test
    public void itGetsFile1() throws Exception {
        String method = "GET";
        String requestPath = "/file1";
        String data = "localhost:5000";
        String byteCount = "";
        byte[] file = fileRetriever.getFile();

        assertEquals(new String(file), new String(fileRouter.routeFiles(method, requestPath, data, byteCount)));
    }

    @Test
    public void itGetsFile2() throws Exception {
        String method = "GET";
        String requestPath = "/file2";
        String data = "localhost:5000";
        String byteCount = "";
        byte[] file = fileRetriever.getFile2();

        assertEquals(new String(file), new String(fileRouter.routeFiles(method, requestPath, data, byteCount)));
    }

    @Test
    public void itGetsJPEGImage() throws Exception {
        String method = "GET";
        String requestPath = "/image.jpeg";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] jpeg = fileRetriever.getJPEG();
        int jpegImageLength = jpeg.length;

        assertEquals(jpegImageLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
    }

    @Test
    public void itGetsPNGImage() throws Exception {
        String method = "GET";
        String requestPath = "/image.png";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] png = fileRetriever.getPNG();
        int pngImageLength = png.length;

        assertEquals(pngImageLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
    }

    @Test
    public void itGetsGIF() throws Exception {
        String method = "GET";
        String requestPath = "/image.gif";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] gif = fileRetriever.getGIF();
        int gifImageLength = gif.length;

        assertEquals(gifImageLength, fileRouter.routeFiles(method, requestPath, data, byteCount).length);
    }

    @Test
    public void itGetsHTMLPage() throws Exception {
        String method = "GET";
        String requestPath = "/";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] html = fileRetriever.getHTMLPage();

        assertEquals(new String(html), new String(fileRouter.routeFiles(method, requestPath, data, byteCount)));
    }

    @Test
    public void returnsEncodedURLWhenDecodedValueIsRequested() throws Exception {
        String method = "GET";
        String requestPath = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String data = "localhost:5000";
        String byteCount = "";

        byte[] body = fileRetriever.getDecoded();

        assertEquals(new String(body), new String(fileRouter.routeFiles(method, requestPath, data, byteCount)));
    }
}