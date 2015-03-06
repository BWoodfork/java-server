package com.httpserver.request;

public class Request {
  private String httpMethod;
  private String uri;
  private String headerField;
  private String byteRange;
  private String fullRequest;
  private String basicAuthCredentials;
  private boolean basic;
  private String etag;
  private String parameterValues;

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

  public boolean isARootRequest() {
    return getURI().equals("/");
  }
  
  public void setBasicRequestStatus(boolean basic) {
    this.basic = basic;
  }
  
  public boolean isABasicAuthRequest() {
    return basic;
  }
  
  public void setBasicAuthCredentials(String basicAuthCredentials) {
    this.basicAuthCredentials = basicAuthCredentials;
  }
  
  public String getBasicAuthCredentials() {
    return basicAuthCredentials;
  }

  public void setEtag(String etag) {
    this.etag = etag;
  }

  public String getEtag() {
    return etag;
  }

  public void setParameterValues(String parameterValues) {
    this.parameterValues = parameterValues;
  }
  
  public String getParameterValues() {
    return parameterValues;
  }
}