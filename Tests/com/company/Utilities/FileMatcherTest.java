package com.company.Utilities;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FileMatcherTest {
    @Test
    public void returnsAListOfTheFileNamesFromCobSpecPublicDirectory() throws Exception {
        ArrayList<String> endPoints = new ArrayList<>();

        File directory = new File("../cob_spec/public");

        File[] fileList = directory.listFiles();

        for (File aFileList : fileList) {
            endPoints.add("/" + aFileList.getName());
        }

        assertEquals(endPoints, FileMatcher.getDirectoryFileNames());
    }
    
    @Test
    public void matchesTheRequestedFileWithTheFileInThePublicDirectoryAndReturnsTheStringName() throws Exception {
        String filePath = "/file1";
        
        assertEquals("/file1", FileMatcher.matchRequestedFile(filePath));
    }
    
    @Test
    public void matchestTheRequestedFile2File() {
        String filePath = "/file2";
        
        assertEquals("/file2", FileMatcher.matchRequestedFile(filePath));
    }
    
    @Test
    public void returnsFileNotFoundStringWhenFileDoesNotExistInPublicDirectory() throws Exception {
        String filePath = "/file33";
        
        assertEquals("File not found in cob_spec/public", FileMatcher.matchRequestedFile(filePath));
    }
}