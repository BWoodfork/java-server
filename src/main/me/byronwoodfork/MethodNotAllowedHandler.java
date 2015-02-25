import java.nio.file.Path;
import java.nio.file.Paths;

public class MethodNotAllowedHandler implements Responder {
  private String serverViewsDirectory;
  
  public MethodNotAllowedHandler(String serverViewsDirectory) {
    this.serverViewsDirectory = serverViewsDirectory;
  }

  @Override
  public Path buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(405);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return Paths.get(serverViewsDirectory + "/405.html");
  }
}