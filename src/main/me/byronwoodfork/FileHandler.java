import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
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

  private Path getPath(Request request) {
    String matchedFileString = matchRequestedFile(getDirectoryFileNames(), request);
    return Paths.get(directory + "/" + matchedFileString).toAbsolutePath();
  }

  @Override
  public Path buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return getPath(request);
  }
}