package com.company.Handler;

import java.sql.ResultSet;

public class Routes {
    public static String file1Route() {
        return "/file1";
    }
    
    public static String rootRoute() {
        return "/";
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
    
    public static String file2Route() {
        return "/file2";
    }
    
    public static String redirectRoute() {
        return "/redirect";
    }
    
    public static String logsRoute() {
        return "/logs";
    }
    
    public static String patchContentRoute() {
        return "/patch-content.txt";
    }
    
    public static String formRoute() {
        return "/form";
    }

    public static String notFoundRoute() {
        return "The page you are looking for cannot be found.";
    }
}
