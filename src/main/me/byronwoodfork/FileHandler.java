import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler implements Responder {
  private String directory;
  
  public FileHandler(String directory) {
    this.directory = directory;
  }
  
  public String[] getDirectoryFileNames() {
    File file = new File(directory);
    return file.list();
  }

  public String matchRequestedFile(String[] fileList, Request request) {
    String URI = request.getURI();
    for (String file : fileList) {
      if (file.equals(URI)) return URI;
    }
    
    return URI;
  }

  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    try {
      return Files.readAllBytes(getPath(request));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "File Could Not Be Read".getBytes();
  }

  private Path getPath(Request request) {
    String matchedFileString = matchRequestedFile(getDirectoryFileNames(), request);
    return Paths.get(directory + "/" + matchedFileString).toAbsolutePath();
  }
}