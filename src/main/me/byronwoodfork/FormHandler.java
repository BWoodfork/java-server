import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FormHandler implements Responder {
  private String directory;
  private static HashMap<String, byte[]> formMap = new HashMap<String, byte[]>();
  
  public FormHandler(String directory) {
    this.directory = directory;
  }
  
  @Override
  public Path buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      writeToForm(request);
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return getPath(request);
  }

  private Path writeToForm(Request request) throws IOException {
    return Files.write(getPath(request), getFormMap().get(request.getHTTPMethod()));
  }
  
  private Path getPath(Request request) {
    return Paths.get(directory + "/" + request.getURI()).toAbsolutePath();
  }
  
  private static HashMap<String, byte[]> getFormMap() {
    formMap.put("POST", "data=cosby".getBytes());
    formMap.put("PUT", "heathcliff".getBytes());
    formMap.put("DELETE", "".getBytes());

    return formMap;
  }
}