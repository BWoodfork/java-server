package com.company.Response.Headers;

<<<<<<< HEAD
import com.company.Reponse.Headers.BodyContents;
import com.company.Routes;
import org.junit.Test;

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
=======
/**
 * Created by 8thlight on 1/20/15.
 */
public class BodyContentsTest {
>>>>>>> 30a5e4862ad773ea52696a71d8bfd97ea0a28cf1
}
