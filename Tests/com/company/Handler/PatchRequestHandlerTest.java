package com.company.Handler;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class PatchRequestHandlerTest {
    private PatchRequestHandler patchRequestHandler;
    private Path absolutePath;

    @Before
    public void setUp() throws Exception {
        patchRequestHandler = new PatchRequestHandler();
        absolutePath = Paths.get("../cob_spec/public/patch-content.txt").toAbsolutePath();
    }

    @Test
    public void returnsTrueIfPatchContentFileExists()throws Exception {
        String method = "GET";
        String filePath = "/patch-content.txt";
        String data = "";

        patchRequestHandler.parseRequest(method, filePath, data);

        assertEquals(true, Files.exists(absolutePath));
    }

    @Test
    public void writesToPatchFileWhenPatchRequestIsMade() throws Exception {
        String method = "PATCH";
        String filePath = "/patch-content.txt";
        String data = "60bb224c68b1ed765a0f84d910de58d0beea91c4";

        patchRequestHandler.parseRequest(method, filePath, data);
        String patchContent = new String(Files.readAllBytes(absolutePath));

        assertEquals("patched content", patchContent);
    }

    @Test
    public void writesDefaultContentToPatchFileWhenEtagValueIs69() throws Exception {
        String method = "PATCH";
        String filePath = "/patch-content.txt";
        String data = "69bc18dc1edc9e1316348b2eaaca9df83898249f";

        patchRequestHandler.parseRequest(method, filePath, data);
        String patchContent = new String(Files.readAllBytes(absolutePath));

        assertEquals("default content", patchContent);
    }
}
