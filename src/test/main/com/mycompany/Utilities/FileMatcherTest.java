package main.com.mycompany.Utilities;

import main.com.mycompany.request.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FileMatcherTest {
    private FileMatcher fileMatcher;
    private int port;
    private Request request;
    
    @Before
    public void setUp() throws Exception {
        fileMatcher = new FileMatcher();
    }

    @Test
    public void returnsAListOfTheFileNamesFromCobSpecPublicDirectory() throws Exception {
        ArrayList<String> endPoints = new ArrayList<>();

        File directory = new File("../cob_spec/public");

        File[] fileList = directory.listFiles();
        
        if (fileList != null) 
            for (File aFileList : fileList) {
                endPoints.add("/" + aFileList.getName());
            }
        
        assertEquals(endPoints, fileMatcher.getDirectoryFileNames());
    }

    @Test
    public void matchesTheRequestedFileWithTheFileInThePublicDirectoryAndReturnsTheStringName() throws Exception {
        String filePath = "/file1";

        assertEquals("/file1", fileMatcher.matchRequestedFile(filePath));
    }

    @Test
    public void matchestTheRequestedFile2File() {
        String filePath = "/file2";

        assertEquals("/file2", fileMatcher.matchRequestedFile(filePath));
    }

    @Test
    public void returnsFileNotFoundStringWhenFileDoesNotExistInPublicDirectory() throws Exception {
        String filePath = "/file33";

        assertEquals("The requested file cannot be found", fileMatcher.matchRequestedFile(filePath));
    }
}