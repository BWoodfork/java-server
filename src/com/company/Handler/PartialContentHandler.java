package com.company.Handler;

import java.util.Arrays;

public class PartialContentHandler {
    public String parseRequest(String byteCount) {
        String splitBytes = byteCount.split("bytes=")[1];
        String[] strings = splitBytes.split("Connection:");

        return strings[0];
    }

    public int getMinRange(byte[] fileContents, String byteCount) {
        String[] numbers = parseRequest(byteCount).split("");

        try {
            Integer.parseInt(numbers[0]);
        } catch (Exception e) {
            if (numbers.length == 2) {
                return fileContents.length - (Integer.parseInt(numbers[1]) - 1);
            }
        }

        return Integer.parseInt(numbers[0]);
    }

    public int getMaxRange(byte[] fileContents, String byteCount) {
        String [] numbers = parseRequest(byteCount).split("");

        try {
            Integer.parseInt(numbers[1]);
        } catch (Exception e) {
            if (numbers.length == 2 && (Integer.parseInt(numbers[0]) < 0)) {
                return Integer.parseInt(numbers[0]);
            } else if (numbers.length == 3) {
                return Integer.parseInt(numbers[2]) + 1;
            }
        }

        try {
            return Integer.parseInt(numbers[2] + 1);
        } catch (Exception e) {
            return fileContents.length;
        }
    }

    public byte[] getPartialContents(byte[] fakeFileContent, String byteCount) {
        return Arrays.copyOfRange(fakeFileContent, getMinRange(fakeFileContent, byteCount), getMaxRange(fakeFileContent, byteCount));
    }
}