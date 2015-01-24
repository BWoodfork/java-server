package com.company.Response.Headers;

import com.company.FileRouter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BodyContentsTest {
    private FileRouter fileRouter;
    
    public BodyContentsTest(FileRouter fileRouter) {
        this.fileRouter = fileRouter;
    }
    
    @Test
    public void bodyRespondsWithContent() throws Exception {
        BodyContents bodyContents = new BodyContents(fileRouter);

        String filePath = "/file1";
        String method = "GET";
        String data = "closeHost";
        String byteCount = "";
        
        assertEquals("file1 contents",new String(bodyContents.getBody(method, filePath, data, byteCount)));
    }
}