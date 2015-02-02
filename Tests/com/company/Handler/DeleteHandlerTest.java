package com.company.Handler;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class DeleteHandlerTest {
    private DeleteHandler deleteHandler;
    
    @Before
    public void setUp() throws Exception {
        deleteHandler = new DeleteHandler();
    }
    
    @Test
    public void returnsTrueIfMethodIsADeleteRequest() throws Exception {
        String method = "DELETE";
        
        assertEquals(true, deleteHandler.isADeleteRequest(method));
    }
    
    @Test
    public void returnsFalseIfMethodIsNotADeleteRequest() throws Exception {
        String method = "PATCH";
        
        assertEquals(false, deleteHandler.isADeleteRequest(method));
    }
    
    @Test
    public void deletesFileContentsFromPath() throws Exception {
        String method = "DELETE";
        Path path = Paths.get("../cob_spec/public/cosby-data.txt").toAbsolutePath();
        String emptyString = "";
        String fileContentsString = new String(Files.readAllBytes(deleteHandler.deleteFileContents(method, path)));
        
        assertEquals(emptyString, fileContentsString);
    }
    
    @Test
    public void returnsFileWithUneditedContentsIfNotADeleteRequest() throws Exception {
        String method = "POST";
        Path path = Paths.get("../cob_spec/public/cosby-data.txt").toAbsolutePath();
        Files.write(path, "Some random contents".getBytes());
        String fileContentsString = new String(Files.readAllBytes(deleteHandler.deleteFileContents(method, path)));

        assertEquals("Some random contents", fileContentsString);
    }
}