import java.nio.file.Path;
import java.nio.file.Paths;

public class NotFoundHandler implements Responder {
  private String serverViewsDirectory;
  
  public NotFoundHandler(String serverViewsDirectory) {
    this.serverViewsDirectory = serverViewsDirectory;
  }

  @Override
  public Path buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(404);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return Paths.get(serverViewsDirectory + "/404.html");
  }
}