package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileRouter {
    private FileResponse file;

    public FileRouter() {
        file = new FileResponse();
    }

    public String getFile1(String path) throws IOException {
        try {
            if (path.equals("/file1")) {
                return file.getFile();
            } else if (path.equals("/")) {
                return "<html><body>Hello World</body></html>";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "The Force is not strong with this one.";
    }
}