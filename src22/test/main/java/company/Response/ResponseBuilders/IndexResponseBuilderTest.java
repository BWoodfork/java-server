package main.java.company.Response.ResponseBuilders;

import main.java.company.Response.Headers.Options;
import main.java.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class IndexResponseBuilderTest {
    private StatusBuilder statusBuilder;
    private IndexResponseBuilder indexResponseBuilder;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        indexResponseBuilder = new IndexResponseBuilder(statusBuilder);
    }
    
    @Test
    public void returnsTheIndexFileADirectoryOfCobSpecFilesFromPublicDirectory() throws Exception {
        Request request = new Request("GET /");
        Options options = new Options();
        Path path = Paths.get("../cob_spec/public/index.html");
        byte[] file = Files.readAllBytes(path);

        assertEquals(new String(file), new String(indexResponseBuilder.buildResponse(request, options)));
    }
    
    @Test
    public void returnsErrorMessageWhenMethodIsNotAGetRequest() throws Exception {
        Request request = new Request("POST /");
        Options options = new Options();
        
        assertEquals("The method you have requested is not valid", new String(indexResponseBuilder.buildResponse(request, options)));
    }
}