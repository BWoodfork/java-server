package com.company.Handler;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartialContentHandler {
    private static final Pattern bytePattern = Pattern.compile("^([0-9])?-([0-9])?$");

    public static String parseRequest(String byteCount) {
        String splitBytes = byteCount.split("bytes=")[1];
        String[] strings = splitBytes.split("Connection:");

        return strings[0];
    }
    
    public static int getMinRange(String minMatch, String maxMatch, int fileContentLength) {
        if (minMatch != null) {
            return Integer.parseInt(minMatch);
        } else {
            return fileContentLength - (Integer.parseInt(maxMatch) - 1);
        }
    }
    
    public static int getMaxRange(String minMatch, String maxMatch, int fileContentLength) {
        if (maxMatch != null) {
            if (minMatch == null) {
                return fileContentLength;
            } else {
                return Integer.parseInt(maxMatch) + 1;
            }
        } else {
            return fileContentLength;
        }
    }

    public static byte[] getPartialContents(byte[] fileContent, String byteCount) {
        Matcher byteMatcher = bytePattern.matcher(parseRequest(byteCount));
        byteMatcher.matches();
        
        int minRange = getMinRange(byteMatcher.group(1), byteMatcher.group(2), fileContent.length);
        int maxRange = getMaxRange(byteMatcher.group(1), byteMatcher.group(2), fileContent.length);
        return Arrays.copyOfRange(fileContent, minRange, maxRange);
    }
}