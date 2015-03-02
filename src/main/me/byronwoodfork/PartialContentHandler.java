import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartialContentHandler implements Responder {
  private String directory;
  private static final Pattern bytePattern = Pattern.compile("^([0-9])?-([0-9])?$");

  public PartialContentHandler(String directory) {
    this.directory = directory;
  }
  
  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    byte[] partialContentFile = new byte[0];
    
    try {
      httpStatusCodes.setStatus(206);
      partialContentFile = Files.readAllBytes(Paths.get(directory + "/" + request.getURI()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    Matcher byteMatcher = bytePattern.matcher(request.getByteRange());
    byteMatcher.matches();

    int minRange = getMinRange(byteMatcher.group(1), byteMatcher.group(2), partialContentFile.length);
    int maxRange = getMaxRange(byteMatcher.group(1), byteMatcher.group(2), partialContentFile.length);

    return Arrays.copyOfRange(partialContentFile, minRange, maxRange);
  }
  
  public int getMinRange(String minMatch, String maxMatch, int fileContentLength) {
    if (minMatch != null) {
      return Integer.parseInt(minMatch);
    } else {
      return fileContentLength - (Integer.parseInt(maxMatch) - 1);
    }
  }
  
  public int getMaxRange(String minMatch, String maxMatch, int fileContentLength) {
    if (maxMatch != null) {
      if (minMatch == null) {
        return fileContentLength;
      } else {
        return Integer.parseInt(maxMatch) + 1;
      }
    } else {
      return fileContentLength;
    }
  }
}