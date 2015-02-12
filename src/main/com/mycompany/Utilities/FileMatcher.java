package main.com.mycompany.Utilities;

import java.io.File;
import java.util.ArrayList;

public class FileMatcher {

    public ArrayList<String> getDirectoryFileNames() {
        ArrayList<String> endPoints = new ArrayList<>();

        File directory = new File("../cob_spec/public");

        File[] fileList = directory.listFiles();

        if (fileList != null)
            for (File aFileList : fileList) {
                endPoints.add("/" + aFileList.getName());
            }

        return endPoints;
    }
    
    public String matchRequestedFile(String request) {
        ArrayList<String> directoryFileNames = getDirectoryFileNames();

        for (String fileName : directoryFileNames) {
            if (request.equals(fileName)) {
                return fileName;
            }
        }
        
        return "The requested file cannot be found";
    }
}