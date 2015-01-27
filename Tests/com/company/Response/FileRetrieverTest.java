package com.company.Response;

import com.company.Routes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileRetrieverTest {
    @Test
    public void matchesTheRequestedFileWithTheFileInThePublicDirectoryAndReturnsTheStringName() throws Exception {
        Routes routes = new Routes();
        FileRetriever fileRetriever = new FileRetriever(routes);
        
        String directoryPath = "/file1";
        
        assertEquals("/file1",fileRetriever.matchRequestedFilePath(directoryPath));
    }
    
    @Test
    public void matchesFile2IfRequested() throws Exception {
        Routes routes = new Routes();
        FileRetriever fileRetriever = new FileRetriever(routes);
        
        String directoryPath = "/file2";
        
        assertEquals("/file2", fileRetriever.matchRequestedFilePath(directoryPath));
    }
    
//    @Test
//    public void returnsTheFileContentsInBytesBasedOnTheRequestBeingMatched() throws Exception {
//        Routes routes = new Routes();
//        FileRetriever fileRetriever = new FileRetriever(routes);
//
//        String directoryPath = "../cob_spec/public/file1";
//        String request = "/file1";
//
//        fileRetriever.readFileBytesFromPath(directoryPath);
//
//        assertEquals("file1 contents", new String(fileRetriever.getFile(request)));
//    }
}
