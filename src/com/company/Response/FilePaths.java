package com.company.Response;

import com.company.Utilities.FileMatcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePaths {

    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public byte[] getFile(String filePath) throws Exception {
        return readFileBytesFromPath("../cob_spec/public" + FileMatcher.matchRequestedFile(filePath));
    }

    public byte[] getIndex() throws Exception {
        return readFileBytesFromPath("../cob_spec/public/index.html"); 
    }
    
    public byte[] getPatchContentFile() throws Exception {
        return readFileBytesFromPath("../cob_spec/extension-files/patch-content.txt");
    }

    public byte[] getPartialContentFile() throws Exception {
        return readFileBytesFromPath("../cob_spec/extension-files/partial_content.txt");
    }

    public byte[] getPostDataFile() throws Exception {
        return readFileBytesFromPath("../cob_spec/public/cosby-data.txt");
    }

    public byte[] redirect() {
        String refreshHTML = "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>";
        return refreshHTML.getBytes();
    }
}