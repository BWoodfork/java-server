import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class Response {
  private Path path;
  private Routes routes;
  private HTTPStatusCodes httpStatusCodes;
  
  public Response(HTTPStatusCodes httpStatusCodes) {
    routes = new Routes();
    this.httpStatusCodes = httpStatusCodes;
  }

  public byte[] getHTTPStatusMessage() {
    return httpStatusCodes.getFormattedStatusMessage().getBytes();
  }

  public byte[] getHTTPMessageBody(Request request) {
    path = routes.getHandler(request).buildResponse(request, httpStatusCodes);

    try {
      return Files.readAllBytes(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return "These are not the droids you are looking for".getBytes();
  }
  
  public byte[] getContentType(Request request) {
    String type = URLConnection.guessContentTypeFromName(request.getURI());
    if (type == null) {
      type = "text/html";
    }
    
    String formatted = "Content-Type: " + type + "\r\n";
    return formatted.getBytes();
  }
  
  public byte[] getContentLength(Request request) {
    String type = Integer.toString(getHTTPMessageBody(request).length) + "\r\n\r\n";
    return type.getBytes();
  }
  
  public byte[] getOptions() {
    return "Allow: GET\r\n".getBytes();
  }
}