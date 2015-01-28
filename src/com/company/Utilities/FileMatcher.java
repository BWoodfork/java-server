package com.company.Utilities;

import java.io.File;
import java.util.ArrayList;

public class FileMatcher {

    public static ArrayList<String> getDirectoryFileNames() {
        ArrayList<String> endPoints = new ArrayList<>();

        File directory = new File("../cob_spec/public");

        File[] fileList = directory.listFiles();

        if (fileList != null)
            for (File aFileList : fileList) {
                endPoints.add("/" + aFileList.getName());
            }

        return endPoints;
    }
    
    public static String matchRequestedFile(String request) {
        ArrayList<String> directoryFileNames = getDirectoryFileNames();

        for (String fileName : directoryFileNames) {
            if (request.equals(fileName)) {
                return fileName;
            }
        }
        
        return "File not found in cob_spec/public";
    }
}