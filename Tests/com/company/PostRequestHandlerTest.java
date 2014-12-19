package com.company;

import com.company.Reponse.FileRetriever;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class PostRequestHandlerTest {
    private PostRequestHandler postRequestHandler;

    @Before
    public void setUp() throws Exception {
        postRequestHandler = new PostRequestHandler();
    }

    @Test
    public void returnCosbyDataWithPostRequest() throws Exception {
        String method = "POST";
        String filePath = "/form";

        postRequestHandler.parseRequest(method, filePath);
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/cosby-data.txt");
        String cosbyString = new String(Files.readAllBytes(path));

        assertEquals("data=cosby", cosbyString);
    }

    @Test
    public void returnsTrueIfFileExists() throws Exception {
        File file = new File("/Users/8thlight/projects/cob_spec/public/cosby-data.txt");

        String method = "GET";
        String filePath = "/form";

        postRequestHandler.parseRequest(method, filePath);

        assertEquals(true, file.exists());
    }

    @Test
    public void writeThePutValueToAFileWhenARequestIsMade() throws Exception {
        String method = "PUT";
        String filePath = "/form";

        postRequestHandler.parseRequest(method, filePath);
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/cosby-data.txt");
        String cosbyString = new String(Files.readAllBytes(path));

        assertEquals("data=heathcliff", cosbyString);
    }

    @Test
    public void ErasesTheDataAfterTheDeleteRequest() throws Exception {
        String method = "DELETE";
        String filePath = "/form";

        postRequestHandler.parseRequest(method, filePath);
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/cosby-data.txt");
        String cosbyString = new String(Files.readAllBytes(path));

        assertEquals("Content Removed", cosbyString);
    }

}
