import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartialContentHandler implements Responder {
  private String directory;
  private PartialContentParser partialContentParser;

  public PartialContentHandler(String directory) {
    this.directory = directory;
    this.partialContentParser = new PartialContentParser();
  }
  
  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(206);
      return partialContentParser.getPartialContent(request, Files.readAllBytes(Paths.get(directory + "/" + request.getURI())));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "Request Invalid".getBytes();
  }
}