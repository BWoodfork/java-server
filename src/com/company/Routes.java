package com.company;

import java.io.File;
import java.util.ArrayList;

public class Routes {
    
    public ArrayList<String> getDirectoryFileNames() {
        ArrayList<String> endPoints = new ArrayList<>();
        
        File directory = new File("../cob_spec/public");

        File[] fileList = directory.listFiles();

            for (File aFileList : fileList) {
                endPoints.add("/" + aFileList.getName());
            }
        
        return endPoints;
    }

    public static String redirectRoute() {
        return "/redirect";
    }
    
    public static String logsRoute() {
        return "/logs";
    }
    
    public static String logRoute() {
        return "/log";
    }
    
    public static String patchContentRoute() {
        return "/patch-content.txt";
    }

    public static String formRoute() {
        return "/form";
    }

    public static String methodOptionsRoute() {
        return "/method_options";
    }

    public static String textFileRoute() {
        return "/text-file.txt";
    }

    public static String partialContentRoute() {
        return "/partial_content.txt";
    }

    public static String notFoundRoute() {
        return "The page you are looking for cannot be found.";
    }

    public static String parametersRoute() {
        return "/parameters?";
    }

    public static String rootRoute() {
        return "/";
    }

    public static String file2Route() {
        return "/file2";
    }

    public static String file1Route() {
        return "/file1";
    }

    public static String jpegRoute() {
        return "/image.jpeg";
    }

    public static String pngRoute() {
        return "/image.png";
    }

    public static String gifRoute() {
        return "/image.gif";
    }
}
