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

    public byte[] readJPEG() throws IOException {
        Path path = Paths.get("/Users/ByronWoodfork/Projects/cob-spec/cob_spec/public", "image.jpeg");
        return Files.readAllBytes(path);
    }

    public byte[] getHTMLPage() {
        String html = "<html><body>Hello World!</body></html>";
        return html.getBytes();
    }

//    public byte[] convertJPEG() {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write("/Users/ByronWoodfork/Projects/cob-spec/cob_spec/public/image.jpeg", "jpeg", byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }

//    public byte[] convertJPEG() throws IOException {
//        BufferedImage image = ImageIO.read(new File("/Users/ByronWoodfork/Projects/cob-spec/cob_spec/public/image.jpeg"));
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ImageIO.write(image, "jpeg", byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }


}