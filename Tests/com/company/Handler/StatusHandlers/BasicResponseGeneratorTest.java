package com.company.Handler.StatusHandlers;

import com.company.Response.ResponseGenerators.BasicRouteGenerator;
import com.company.Utilities.FileMatcher;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicResponseGeneratorTest {
    private BasicRouteGenerator basicResponseGenerator;
    private StatusBuilder statusBuilder;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        basicResponseGenerator = new BasicRouteGenerator(statusBuilder);
    }
    
    @Test
    public void readsTheBytesOfTheFileWhenGivenADirectoryPath() throws Exception {
        String directoryPath = "../cob_spec/public/file1";
        
        assertEquals("file1 contents", new String(basicResponseGenerator.readFileBytesFromPath(directoryPath)));
    }
    
    @Test
    public void returnsTheBytesOfFile2() throws Exception {
        String directoryPath = "../cob_spec/public/file2";

        assertEquals("file2 contents", new String(basicResponseGenerator.readFileBytesFromPath(directoryPath)));
    }
    
    @Test
    public void returnsTheFileNameWhenFileMatcherFindsAMatch() throws Exception {
        Request request = new Request("GET /file1");
        
        assertEquals("/file1" , basicResponseGenerator.getMatchedFileName(request));
    }
    
    @Test
    public void setsTheHTTPStatusTo404IfTheRequestedFileCannotBeFound() throws Exception {
        FileMatcher fileMatcher = new FileMatcher();
        Request request = new Request("GET /file22");
        
        fileMatcher.matchRequestedFile("/file22");
        basicResponseGenerator.getMatchedFileName(request);

        assertEquals("HTTP/1.1 404 NOT FOUND\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void setsTheHTTPStatusTo200OkIfTheRequestedFileIsFound() throws Exception {
        FileMatcher fileMatcher = new FileMatcher();
        Request request = new Request("Get /file1");
        
        fileMatcher.matchRequestedFile("/file1");
        basicResponseGenerator.getMatchedFileName(request);
        
        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void setsTheHTTPStatusTo200OKForFile2() throws Exception {
        FileMatcher fileMatcher = new FileMatcher();
        Request request = new Request("Get /file2");

        fileMatcher.matchRequestedFile("/file2");
        basicResponseGenerator.getMatchedFileName(request);

        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
}