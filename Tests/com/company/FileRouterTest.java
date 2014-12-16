package com.company;

import Reponse.FileResponse;
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
        String path = "GET /file1 HTTP/1.1Connection: closeHost: localhost:5000";
        byte[] file = fileresponse.getFile();
        int fileLength = file.length;

        assertEquals(fileLength, fileRouter.routeFiles(path).length);
    }

    @Test
    public void itGetsFile2() throws Exception {
        String path = "GET /file2 HTTP/1.1Connection: closeHost: localhost:5000";
        byte[] file = fileresponse.getFile();
        int fileLength = file.length;

        assertEquals(fileLength, fileRouter.routeFiles(path).length);
    }

    @Test
    public void itGetsJPEGImage() throws Exception {
        String path = "GET /image.jpeg HTTP/1.1Connection: closeHost: localhost:5000";

        byte[] jpeg = fileresponse.getJPEG();
        int jpegImageLength = jpeg.length;

        assertEquals(jpegImageLength, fileRouter.routeFiles(path).length);
    }

    @Test
    public void itGetsPNGImage() throws Exception {
        String path = "GET /image.png HTTP/1.1Connection: closeHost: localhost:5000";

        byte[] png = fileresponse.getPNG();
        int pngImageLength = png.length;

        assertEquals(pngImageLength, fileRouter.routeFiles(path).length);
    }

    @Test
    public void itGetsGIF() throws Exception {
        String path = "GET /image.gif HTTP/1.1Connection: closeHost: localhost:5000";

        byte[] gif = fileresponse.getGIF();
        int gifImageLength = gif.length;

        assertEquals(gifImageLength, fileRouter.routeFiles(path).length);
    }

    @Test
    public void itGetsHTMLPage() throws Exception {
        String path = "GET / HTTP/1.1Connection: closeHost: localhost:5000";

        byte[] html = fileresponse.getHTMLPage();
        int htmlPageLength = html.length;

        assertEquals(htmlPageLength, fileRouter.routeFiles(path).length);
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