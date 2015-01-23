package com.company.Response;

import com.company.Response.Headers.BodyContents;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BodyContentsTest {
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