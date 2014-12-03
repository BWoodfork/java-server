package com.company;

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
        String path = "/file1";
        byte[] file = fileresponse.getFile();
        int fileLength = file.length;

        assertEquals(fileLength, fileRouter.routeFiles(path).length);
    }

    @Test
    public void itGetsJPEGImage() throws Exception {
        String path = "/image.jpeg";

        byte[] jpeg = fileresponse.getJPEG();
        int jpegImageLength = jpeg.length;

        assertEquals(jpegImageLength, fileRouter.routeFiles(path).length);
    }

    @Test
    public void itGetsPNGImage() throws Exception {
        String path = "/image.png";

        byte[] png = fileresponse.getPNG();
        int pngImageLength = png.length;

        assertEquals(pngImageLength, fileRouter.routeFiles(path).length);
    }

    @Test
    public void itGetsGIF() throws Exception {
        String path = "/image.gif";

        byte[] gif = fileresponse.getGIF();
        int gifImageLength = gif.length;

        assertEquals(gifImageLength, fileRouter.routeFiles(path).length);
    }

    @Test
    public void itGetsHTMLPage() throws Exception {
        String path = "/";

        byte[] html = fileresponse.getHTMLPage();
        int htmlPageLength = html.length;

        assertEquals(htmlPageLength, fileRouter.routeFiles(path).length);
    }
}