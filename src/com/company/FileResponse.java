package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileResponse {
    public String getFile() throws IOException {
        FileReader fileReader = new FileReader("/Users/ByronWoodfork/Projects/cob-spec/cob_spec/public/file1");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.readLine();
    }
}