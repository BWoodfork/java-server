package com.company;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class PatchRequestHandlerTest {
    private PatchRequestHandler patchRequestHandler;

    @Before
    public void setUp() throws Exception {
        patchRequestHandler = new PatchRequestHandler();
    }

    @Test
    public void returnsTrueIfPatchContentFileExists()throws Exception {
        String method = "GET";
        String filePath = "/patch-content.txt";
        String data = "";

        patchRequestHandler.parseRequest(method, filePath, data);
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/patch-content.txt");
//        String patchContent = new String(Files.readAllBytes(path));

        assertEquals(true, Files.exists(path));
    }

    @Test
    public void writesToPatchFileWhenPatchRequestIsMade() throws Exception {
        String method = "PATCH";
        String filePath = "/patch-content.txt";
        String data = "60bb224c68b1ed765a0f84d910de58d0beea91c4Connection";

        patchRequestHandler.parseRequest(method, filePath, data);
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/patch-content.txt");
        String patchContent = new String(Files.readAllBytes(path));

        assertEquals("patched content", patchContent);
    }

    @Test
    public void writesDefaultContentToPatchFileWhenEtagValueIs69() throws Exception {
        String method = "PATCH";
        String filePath = "/patch-content.txt";
        String data = "69bc18dc1edc9e1316348b2eaaca9df83898249f";

        patchRequestHandler.parseRequest(method, filePath, data);
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/patch-content.txt");
        String patchContent = new String(Files.readAllBytes(path));

        assertEquals("default content", patchContent);
    }
}
