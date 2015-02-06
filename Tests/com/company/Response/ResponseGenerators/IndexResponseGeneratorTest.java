package com.company.Response.ResponseGenerators;

import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class IndexResponseGeneratorTest {
    @Test
    public void returnsTheIndexFileADirectoryOfCobSpecFilesFromPublicDirectory() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        IndexIResponseGenerator indexResponseGenerator = new IndexIResponseGenerator(statusBuilder);
        Request request = new Request("GET /");
        Path path = Paths.get("../cob_spec/public/index.html");
        byte[] file = Files.readAllBytes(path);

        assertEquals(new String(file), new String(indexResponseGenerator.getBody(request)));
    }
    
    @Test
    public void returnsErrorMessageWhenMethodIsNotAGetRequest() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        IndexIResponseGenerator indexResponseGenerator = new IndexIResponseGenerator(statusBuilder);
        Request request = new Request("POST /");
        
        assertEquals("The method you have requested is not valid", new String(indexResponseGenerator.getBody(request)));
    }
}
