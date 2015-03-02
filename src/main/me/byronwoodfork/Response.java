import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLConnection;

public class Response {
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
    return routes.getHandler(request).buildResponse(request, httpStatusCodes);
  }

  public byte[] getLocation() {
    String location = "Location: http://localhost:5000/" + "\r\n";

    return location.getBytes();
  }
  
  public byte[] getContentType(Request request) {
    String type = URLConnection.guessContentTypeFromName(request.getURI());
    if (type == null) {
      type = "text/plain";
    }
    
    String formatted = "Content-Type: " + type + "\r\n";
    return formatted.getBytes();
  }
  
  public byte[] getContentLength(Request request) {
    String httpMessageBodySize = Integer.toString(getHTTPMessageBody(request).length);
    String formattedSizeHeader = "Content-Length: " + httpMessageBodySize + "\r\n\r\n";
    return formattedSizeHeader.getBytes();
  }
  
  public byte[] getAllowHeader(Request request) {
    String allowHeader = "Allow: " + routes.getOptions(request) + "\r\n";
    
    return allowHeader.getBytes();
  }
  
  public byte[] getResponse(Request request) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte[] body = getHTTPMessageBody(request);
    
    byteArrayOutputStream.write(getHTTPStatusMessage());
    byteArrayOutputStream.write(getLocation());
    byteArrayOutputStream.write(getContentType(request));
    byteArrayOutputStream.write(getAllowHeader(request));
    byteArrayOutputStream.write(getContentLength(request));
    byteArrayOutputStream.write(body);
    
    return byteArrayOutputStream.toByteArray();
  }
}