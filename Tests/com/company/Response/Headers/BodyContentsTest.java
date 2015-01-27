package com.company.Response.Headers;

import com.company.FileRouter;
import com.company.Handler.PatchRequestHandler;
import com.company.Handler.PostRequestHandler;
import com.company.Response.FileRetriever;
import com.company.Routes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BodyContentsTest {
    private FileRouter fileRouter;
    
//    public BodyContentsTest(FileRouter fileRouter) {
//        this.fileRouter = fileRouter;
//    }
    
    @Before
    public void setUp() throws Exception {
        Routes routes = new Routes();
        FileRetriever fileRetriever = new FileRetriever(routes);
        PostRequestHandler postRequestHandler = new PostRequestHandler(fileRetriever);
        PatchRequestHandler patchRequestHandler = new PatchRequestHandler(fileRetriever);
        
        fileRouter = new FileRouter(postRequestHandler, patchRequestHandler);
    }
    
    @Test
    public void bodyRespondsWithContent() throws Exception {
        BodyContents bodyContents = new BodyContents();

        String filePath = "/file1";
        String method = "GET";
        String data = "closeHost";
        String byteCount = "";

        assertEquals("file1 contents",new String(bodyContents.getBody(method, filePath, data, byteCount)));
    }
}