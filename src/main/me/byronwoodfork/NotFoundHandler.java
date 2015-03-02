import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NotFoundHandler implements Responder {
  private String serverViewsDirectory;
  
  public NotFoundHandler(String serverViewsDirectory) {
    this.serverViewsDirectory = serverViewsDirectory;
  }

  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(404);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      return Files.readAllBytes(Paths.get(serverViewsDirectory + "/404.html"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "File Could Not Be Read".getBytes();
  }
}