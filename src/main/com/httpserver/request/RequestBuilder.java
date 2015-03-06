package com.httpserver.request;

public class RequestBuilder {
  private String httpMethod;
  private String uri;
  private String headerField;
  private String byteRange;
  private boolean basicRequestStatus;
  private String basicAuthCredentials;
  private String etag;
  private String parameterValues;

  public Request build() {
    return new Request();
  }

  public RequestBuilder setHTTPMethod(String HTTPMethod) {
    this.httpMethod = HTTPMethod;
    return this;
  }

  public RequestBuilder setURI(String uri) {
    this.uri = uri;
    return this;
  }

  public RequestBuilder setHeaderField(String headerField) {
    this.headerField = headerField;
    return this;
  }

  public RequestBuilder setByteRange(String byteRange) {
    this.byteRange = byteRange;
    return this;
  }
  
  public RequestBuilder setBasicRequestStatus(boolean basicRequestStatus) {
    this.basicRequestStatus = basicRequestStatus;
    return this;
  }
  
  public RequestBuilder setBasicAuthCredentials(String basicAuthCredentials) {
    this.basicAuthCredentials = basicAuthCredentials;
    return this;
  }
  
  public RequestBuilder setEtag(String etag) {
    this.etag = etag;
    return this;
  }
  
  public RequestBuilder setParameterValues(String parameterValues) {
    this.parameterValues = parameterValues;
    return this;
  }
}
