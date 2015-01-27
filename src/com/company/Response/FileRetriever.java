package com.company.Response;

import com.company.Handler.CobSpec.CobSpecFileHandler;
import com.company.Routes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class FileRetriever {
    private Routes routes;
    
    public FileRetriever(Routes routes) {
        this.routes = routes;
    }
    
    public byte[] readFileBytesFromPath(String directoryPath) throws Exception {
        Path absolutePath = Paths.get(directoryPath).toAbsolutePath();
        return Files.readAllBytes(absolutePath);
    }

    public String matchRequestedFilePath(String request) {
        ArrayList<String> directoryFileNames = routes.getDirectoryFileNames();

        for (String fileName : directoryFileNames) {
            if (request.equals(fileName)) {
                return fileName;
            }
        }
        
        return "File not Found";
    }

    public byte[] getFile(String method, String filePath, String data, String byteCount) throws Exception {
        CobSpecFileHandler cobSpecFileHandler = new CobSpecFileHandler();
        
        if (filePath.equals(matchRequestedFilePath(filePath))) {
            return readFileBytesFromPath("../cob_spec/public" + matchRequestedFilePath(filePath));
        } else if (!filePath.equals(matchRequestedFilePath(filePath))) {
            return cobSpecFileHandler.getResponseForCobSpecTests(method, filePath, data, byteCount);
        } else return Routes.notFoundRoute().getBytes();
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