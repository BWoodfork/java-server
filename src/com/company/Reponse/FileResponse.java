package com.company.Reponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileResponse {
    public byte[] getFile() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/file1");
        return Files.readAllBytes(path);
    }

    public byte[] getFile2() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/file2");
        return Files.readAllBytes(path);
    }

    public byte[] getJPEG() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public", "image.jpeg");
        return Files.readAllBytes(path);
    }

    public byte[] getPNG() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public", "image.png");
        return Files.readAllBytes(path);
    }

    public byte[] getGIF() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public", "image.gif");
        return Files.readAllBytes(path);
    }

    public byte[] getHTMLPage() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/java-server/extension-files", "html-page");
        return Files.readAllBytes(path);
    }

    public byte[] getDecoded() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/java-server/extension-files", "encode-these-variables");
        return Files.readAllBytes(path);
    }

    public byte[] refresh() {
        String refreshHTML = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>";
        return refreshHTML.getBytes();
    }

    public byte[] getFirstPartialContent() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/partial_content.txt");
        return Files.readAllBytes(path);
    }

    public byte[] cosbyData() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/cosby-data.txt");
        return Files.readAllBytes(path);
    }

    public byte[] patchContent() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/cob_spec/public/patch-content.txt");
        return Files.readAllBytes(path);
    }
}