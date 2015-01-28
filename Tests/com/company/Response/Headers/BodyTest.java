package com.company.Response.Headers;

import com.company.Response.Body;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BodyTest {

    @Test
    public void bodyRespondsWithContent() throws Exception {
        Body body = new Body();

        String filePath = "/file1";
        String method = "GET";
        String data = "closeHost";
        String byteCount = "";

        assertEquals("file1 contents",new String(body.getBody(method, filePath, data, byteCount)));
    }
}