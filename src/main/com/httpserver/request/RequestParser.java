package com.httpserver.request;

import javax.xml.bind.DatatypeConverter;
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
    
    request.setFullRequest(requestString);
    request.setHTTPMethod(parseHTTPMethod());
    request.setURI(parseURI());
    request.setHeaderField(parseHeaderField());
    request.setByteRange(getByteRange());
    request.setBasicRequestStatus(isABasicAuthRequest());
    request.setBasicAuthCredentials(parseBasicAuthCredentials());
    request.setEtag(parseEtag());
    request.setParameterValues(getDecodedParameterKey());

    return request;
  }
  
  private String convertRequestToString() throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    StringBuilder requestBuilder = new StringBuilder();
    String line;
    try {
      do {
        line = bufferedReader.readLine();
        requestBuilder.append(line);
        if (line.equals("")) break;
      } while (bufferedReader.ready());
    } catch (Exception e) {
      System.out.println("Request could not be read");
    }

    return requestBuilder.toString();
  }

  private String parseHTTPMethod() throws IOException {
    return requestArray[0];
  }

  private boolean isARootRequest() {
    return (requestArray[1].equals("/"));
  }

  private String parseURI() throws IOException {
    if (isAParameterRequest()) return "parameters";
    if (isARootRequest()) return "/";

    String[] splitOnBackslash = requestArray[1].split("/");
    return splitOnBackslash[1];
  }

  private String parseHeaderField() throws IOException {
    String thirdElement = requestArray[2];
    int lastIndex = thirdElement.lastIndexOf("1");
    String protocol = thirdElement.substring(0, lastIndex + 1);
    String[] splitOnProtocol = thirdElement.split(protocol);

    return splitOnProtocol[1];
  }

  private String getByteRange() throws IOException {
    try {
      String[] splitOnConnection = requestArray[3].split("Connection:");
      String[] splitOnBytes = splitOnConnection[0].split("bytes=");
      return splitOnBytes[1];
    } catch (ArrayIndexOutOfBoundsException e){

      return "No Range Given";
    }
  }

  private boolean isAParameterRequest() throws IOException {
    try {
      String secondElement = requestArray[1];
      String[] splitOnMark = secondElement.split("\\?");
      String parameterString = splitOnMark[0];

      return parameterString.equals("/parameters");
    } catch (ArrayIndexOutOfBoundsException e) {

      return false;
    }
  }

  private String getDecodedParameterKey() throws IOException {
    String[] splitOnMark = requestArray[1].split("\\?");

    try {
      String parameters = splitOnMark[1];
      String paramsWithSpace = parameters.replaceAll("=", " = ");
      String paramsWithAmpSpace = paramsWithSpace.replaceAll("&", " ");
      return URLDecoder.decode(paramsWithAmpSpace, "UTF-8");
    } catch (ArrayIndexOutOfBoundsException e) {

      return "No Parameter Key Exists";
    }
  }
  
  private boolean isABasicAuthRequest() throws IOException {
    return requestArray[3].equals("Basic");
  }

  private String parseBasicAuthCredentials() throws IOException {
    String[] splitAtEndOfCredentials = requestArray[4].split("Connection:");

    byte[] base64String = DatatypeConverter.parseBase64Binary(splitAtEndOfCredentials[0]);
    return new String(base64String);
  }

  public String parseEtag() throws IOException {
    String[] splitOnConnection = requestArray[4].split("Connection:");

    return splitOnConnection[0];
  }
}