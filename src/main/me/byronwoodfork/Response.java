import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Response {
  private Path path;
  private Routes routes;
  
  public Response() {
    routes = new Routes();
  }

  public byte[] getHTTPStatusMessage(HTTPStatusCodes httpStatusCodes) {
    return httpStatusCodes.getFormattedStatusMessage().getBytes();
  }

  public byte[] getHTTPMessageBody(Request request, HTTPStatusCodes httpStatusCodes) {
    path = routes.getHandler(request).buildResponse(request, httpStatusCodes);

    try {
      return Files.readAllBytes(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "These are not the droids you are looking for".getBytes();
  }
  
  public byte[] getContentType(Request request, HTTPStatusCodes httpStatusCodes) {
    path = routes.getHandler(request).buildResponse(request, httpStatusCodes);
    
    MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
    String type = mimetypesFileTypeMap.getContentType(path.toFile());
    String formatted = "Content-Type: " + type + "\r\n";
    return formatted.getBytes();
  }
  
  public byte[] getContentLength(Request request, HTTPStatusCodes httpStatusCodes) {
    String type = Integer.toString(getHTTPMessageBody(request, httpStatusCodes).length) + "\r\n\r\n";
    return type.getBytes();
  }
  
  public byte[] getOptions() {
    return "Allow: GET".getBytes();
  }
}