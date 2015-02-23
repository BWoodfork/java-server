import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

public class RequestParser {
  private InputStream inputStream;
  private String[] requestArray;

  public RequestParser(InputStream inputStream) {
    this.inputStream = inputStream;
  }
  
  public Request parse() throws IOException {
    Request request = new Request();
    String requestString = convertRequestToString();
    requestArray = requestString.split(" ");

    request.setHTTPMethod(parseHTTPMethod());
    request.setURI(parseURI());
    request.setHeaderField(parseHeaderField());
    request.setByteRange(getByteRange());
    request.setFullRequest(requestString);
    return request;
  }
  
  private String convertRequestToString() throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    StringBuilder requestBuilder = new StringBuilder();
    String line;

    do {
      line = bufferedReader.readLine();
      requestBuilder.append(line);
      if (line.equals("")) break;
    } while (bufferedReader.ready());

    return requestBuilder.toString();
  }

  private String parseHTTPMethod() throws IOException {
    return requestArray[0];
  }

  public String parseURI() throws IOException {
    return requestArray[1];
  }

  private String parseHeaderField() throws IOException {
    String thirdElement = requestArray[2];
    int lastIndex = thirdElement.lastIndexOf("1");
    String protocol = thirdElement.substring(0, lastIndex + 1);
    String[] splitOnProtocol = thirdElement.split(protocol);

    return splitOnProtocol[1];
  }

  private String getByteRange() throws IOException {
    String[] fourthElement = requestArray[3].split("Connection:");
    
    return fourthElement[0];
  }

  private boolean isAParameterRequest() throws IOException {
    String secondElement = requestArray[1];
    String[] splitOnMark = secondElement.split("\\?");
    String parameterString = splitOnMark[0];
    
    return parameterString.equals("/parameters");
  }

  private String getDecodedParameterKey() throws IOException {
    String[] splitOnMark = requestArray[1].split("\\?");
    String parameters = splitOnMark[1];
    String paramsWithSpace = parameters.replaceAll("=", " = ");
    String paramsWithAmpSpace = paramsWithSpace.replaceAll("&", " ");

    return URLDecoder.decode(paramsWithAmpSpace, "UTF-8");
  }
}