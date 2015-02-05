package com.company.Response.ResponseGenerators;

import com.company.Handler.PutRequestHandler;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class DeleteRequestResponseGeneratorTest {
    private DeleteRequestResponseGenerator deleteRequestResponseGenerator;
    private StatusBuilder statusBuilder;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        deleteRequestResponseGenerator = new DeleteRequestResponseGenerator(statusBuilder);
    }
    
    @Test
    public void returnsTrueIfMethodIsADeleteRequest() throws Exception {
        String method = "DELETE";
        
        assertEquals(true, deleteRequestResponseGenerator.isADeleteRequest(method));
    }
    
    @Test
    public void returnsFalseIfMethodIsNotADeleteRequest() throws Exception {
        String method = "PATCH";
        
        assertEquals(false, deleteRequestResponseGenerator.isADeleteRequest(method));
    }
    
    @Test
    public void deletesContentFromPath() throws  Exception {
        Request request = new Request("PUT /form");
        PutRequestHandler putRequestHandler = new PutRequestHandler();
        PutRequestResponseGenerator putRequestResponseGenerator = new PutRequestResponseGenerator(statusBuilder);
        putRequestHandler.execute();
        putRequestResponseGenerator.getBody(request);
        
        assertEquals("data=heathcliff", new String(putRequestResponseGenerator.getPutContentFile()));
        
        Request request2 = new Request("DELETE /form");
        deleteRequestResponseGenerator.getBody(request2);
        Path path = Paths.get("../cob_spec/public/cosby-data.txt").toAbsolutePath();
        
        assertEquals("", new String(Files.readAllBytes(path)));
    }
}