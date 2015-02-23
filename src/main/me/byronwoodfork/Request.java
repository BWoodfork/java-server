public class Request {
  private String httpMethod;
  private String uri;
  private String headerField;
  private String byteRange;
  private String fullRequest;

  public void setHTTPMethod(String httpMethod) {
    this.httpMethod = httpMethod;
  }

  public void setURI(String uri) {
    this.uri = uri;
  }

  public void setHeaderField(String headerField) {
    this.headerField = headerField;
  }

  public void setByteRange(String byteRange) {
    this.byteRange = byteRange;
  }

  public String getHTTPMethod() {
    return httpMethod;
  }

  public String getURI() {
    return uri;
  }

  public String getHeaderField() {
    return headerField;
  }

  public String getByteRange() {
    return byteRange;
  }

  public void setFullRequest(String fullRequest) {
    this.fullRequest = fullRequest;
  }

  public String getFullRequest() {
    return fullRequest;
  }
}