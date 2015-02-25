import java.nio.file.Path;

public interface Responder {
  public Path buildResponse(Request request, HTTPStatusCodes httpStatusCodes);
}