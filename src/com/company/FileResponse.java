package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileResponse {
    public byte[] getFile() throws IOException {
        FileReader fileReader = new FileReader("/Users/ByronWoodfork/Projects/cob-spec/cob_spec/public/file1");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.readLine().getBytes();
    }

    public byte[] getJPEG() throws IOException {
        Path path = Paths.get("/Users/ByronWoodfork/Projects/cob-spec/cob_spec/public", "image.jpeg");
        return Files.readAllBytes(path);
    }

    public byte[] getPNG() throws IOException {
        Path path = Paths.get("/Users/ByronWoodfork/Projects/cob-spec/cob_spec/public", "image.png");
        return Files.readAllBytes(path);
    }

    public byte[] getGIF() throws IOException {
        Path path = Paths.get("/Users/ByronWoodfork/Projects/cob-spec/cob_spec/public", "image.gif");
        return Files.readAllBytes(path);
    }

    public byte[] getHTMLPage() {
        String html = "<html><body></body></html>";
        return html.getBytes();
    }
}