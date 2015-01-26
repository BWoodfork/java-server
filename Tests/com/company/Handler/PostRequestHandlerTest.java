package com.company.Handler;

import com.company.Response.FileRetriever;
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
        FileRetriever fileRetriever = new FileRetriever();
        postRequestHandler = new PostRequestHandler(fileRetriever);
    }

    @Test
    public void returnCosbyDataWithPostRequest() throws Exception {
        String method = "POST";
        String filePath = "/form";

        postRequestHandler.parseRequest(method, filePath);
        Path path = Paths.get("../cob_spec/public/cosby-data.txt");
        Path absolutePath = path.toAbsolutePath();
        
        String cosbyString = new String(Files.readAllBytes(absolutePath));

        assertEquals("data=cosby", cosbyString);
    }

    @Test
    public void returnsTrueIfFileExists() throws Exception {
        File file = new File("../cob_spec/public/cosby-data.txt");

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
        Path path = Paths.get("../cob_spec/public/cosby-data.txt");
        Path absolutePath = path.toAbsolutePath();
        String cosbyString = new String(Files.readAllBytes(absolutePath));

        assertEquals("data=heathcliff", cosbyString);
    }

    @Test
    public void ErasesTheDataAfterTheDeleteRequest() throws Exception {
        String method = "DELETE";
        String filePath = "/form";

        postRequestHandler.parseRequest(method, filePath);
        Path path = Paths.get("../cob_spec/public/cosby-data.txt");
        Path absolutePath = path.toAbsolutePath();
        String cosbyString = new String(Files.readAllBytes(absolutePath));

        assertEquals("Content Removed", cosbyString);
    }

}
