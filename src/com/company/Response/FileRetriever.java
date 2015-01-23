package com.company.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileRetriever {
    public byte[] getFile() throws IOException {
        Path path = Paths.get("../cob_spec/public/file1");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] getFile2() throws IOException {
        Path path = Paths.get("../cob_spec/public/file2");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] getJPEG() throws IOException {
        Path path = Paths.get("../cob_spec/public/image.jpeg");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] getPNG() throws IOException {
        Path path = Paths.get("../cob_spec/public/image.png");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] getGIF() throws IOException {
        Path path = Paths.get("../cob_spec/public/image.gif");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] getHTMLPage() throws IOException {
        Path path = Paths.get("extension-files/home-page");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] getDecoded() throws IOException {
        Path path = Paths.get("extension-files/encode-these-variables");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] getFirstPartialContent() throws IOException {
        Path path = Paths.get("../cob_spec/public/partial_content.txt");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] cosbyData() throws IOException {
        Path path = Paths.get("../cob_spec/public/cosby-data.txt");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] patchContent() throws IOException {
        Path path = Paths.get("../cob_spec/public/patch-content.txt");
        Path absolutePath = path.toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] redirect() {
        String refreshHTML = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>";
        return refreshHTML.getBytes();
    }}