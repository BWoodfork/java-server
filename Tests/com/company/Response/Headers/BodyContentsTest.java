package com.company.Response.Headers;

import org.junit.Test;

import com.company.Reponse.Headers.BodyContents;
import com.company.Routes;

import static org.junit.Assert.assertEquals;

public class BodyContentsTest {
    @Test
    public void returnFile1ContentsInBody() throws Exception {
        BodyContents bodyContents = new BodyContents();
        String method = "GET";
        String filePath = Routes.file1Route();
        String data = "";
        String byteCount = "";
        
        String bodyContentsString = new String(bodyContents.getBody(method, filePath, data, byteCount));
        String fileContentsString = "file1 contents";
        
        assertEquals(fileContentsString, bodyContentsString);
    }
}
