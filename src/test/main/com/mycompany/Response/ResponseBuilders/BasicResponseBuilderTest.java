package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Utilities.FileMatcher;
import main.com.mycompany.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicResponseBuilderTest {
    private BasicResponseBuilder basicResponseBuilder;
    private StatusBuilder statusBuilder;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        FileMatcher fileMatcher = new FileMatcher();
        basicResponseBuilder = new BasicResponseBuilder(statusBuilder, fileMatcher);
    }
    
    @Test
    public void readsTheBytesOfTheFileWhenGivenADirectoryPath() throws Exception {
        String directoryPath = "../cob_spec/public/file1";
        
        assertEquals("file1 contents", new String(basicResponseBuilder.readFileBytesFromPath(directoryPath)));
    }
    
    @Test
    public void returnsTheBytesOfFile2() throws Exception {
        String directoryPath = "../cob_spec/public/file2";

        assertEquals("file2 contents", new String(basicResponseBuilder.readFileBytesFromPath(directoryPath)));
    }
    
    @Test
    public void returnsTheFileNameWhenFileMatcherFindsAMatch() throws Exception {
        Request request = new Request("GET /file1");
        
        assertEquals("/file1", basicResponseBuilder.getMatchedFileName(request));
    }
    
    @Test
    public void setsTheHTTPStatusTo200OkIfTheRequestedFileIsFound() throws Exception {
        FileMatcher fileMatcher = new FileMatcher();
        Request request = new Request("Get /file1");
        
        fileMatcher.matchRequestedFile("/file1");
        basicResponseBuilder.getMatchedFileName(request);
        
        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void setsTheHTTPStatusTo200OKForFile2() throws Exception {
        FileMatcher fileMatcher = new FileMatcher();
        Request request = new Request("Get /file2");

        fileMatcher.matchRequestedFile("/file2");
        basicResponseBuilder.getMatchedFileName(request);

        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
}