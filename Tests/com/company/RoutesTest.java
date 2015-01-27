package com.company;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RoutesTest {
    @Test
    public void returnsTheFileNamesFromCobSpecPublicDirectory() throws Exception {
        Routes routes = new Routes();
        ArrayList<String> endPoints = new ArrayList<>();
        
        File directory = new File("../cob_spec/public");
        
        File[] fileList = directory.listFiles();
        
        for ( int i = 0; i < fileList.length; i++) {
            endPoints.add("/" + fileList[i].getName());
        }
        
        assertEquals(endPoints, routes.getDirectoryFileNames());
    }
}