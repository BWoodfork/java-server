package com.company;

import com.company.HeaderData.HeaderOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileResponse {
    private HeaderOutput headerOutput;

    public FileResponse() {
        headerOutput = new HeaderOutput();
    }

    public byte[] getFile() throws IOException {
        FileReader fileReader = new FileReader("/Users/8thlight/projects/cob_spec/public/file1");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.readLine().getBytes();
    }

    public byte[] getFile2() throws IOException {
        FileReader fileReader = new FileReader("/Users/8thlight/projects/cob_spec/public/file2");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.readLine().getBytes();
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

    public byte[] getHTMLPage() {
        String html = "<html><body> <ul>" +
                "<li> <a href='/file1'>file1</a> </li>\n" +
                "<li> <a href='/file2'>file2</a> </li>\n" +
                "<li> <a href='/image.jpeg'>image.jpeg</a></li>\n" +
                "<li> <a href='/image.png'>image.png</a></li>\n" +
                "<li> <a href='/image.gif'>image.gif</a></li>\n" +
                "<li> <a href='/partial_content.txt'>partial_content.txt</a></li>\n" +
                "<li> <a href='/text-file.txt'>text-file.txt</a></li>\n" +
                "<li> <a href='/patch-content.txt'>patch-content.txt</a></li>\n" +
                "</ul> </body></html>";
        return html.getBytes();
    }

    public byte[] getAuthenticationRequired() {
        String auth = "Authentication required";
        return auth.getBytes();
    }

    public byte[] getFileLogger() throws IOException {
        Path path = Paths.get("/Users/8thlight/projects/cob_spec", "logs.txt");
        return Files.readAllBytes(path);
    }

    public byte[] getDecoded() {
        String var2 = "variable_2 = stuff";
        String var = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?" + "\r\n" + var2;
        return var.getBytes();
    }

    public byte[] getPartialContent() throws IOException {
        FileReader fileReader = new FileReader("/Users/8thlight/projects/cob_spec/public/partial_content.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String content = bufferedReader.readLine();
        System.out.println(content.substring(5, 76));
        return content.substring(5, 76).getBytes();
    }
}