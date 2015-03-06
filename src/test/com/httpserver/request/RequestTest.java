package com.httpserver.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RequestTest {
  private Request request;

  @Before
  public void setUp() throws Exception {
    request = new Request();
  }

  @Test
  public void returnsTheFullRequestAfterItHasBeenSet() throws Exception {
    request.setFullRequest("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000");
    Assert.assertEquals("GET /partial_content.txt HTTP/1.1Range: bytes=0-4Connection: closeHost: localhost:5000", request.getFullRequest());
  }

  @Test
  public void returnsTheHTTPMethodAfterItHasBeenSet() throws Exception {
    request.setHTTPMethod("GET");
    Assert.assertEquals("GET", request.getHTTPMethod());
  }

  @Test
  public void returnsTheURIAfterItHasBeenSet() throws Exception {
    request.setURI("/file5000");
    Assert.assertEquals("/file5000", request.getURI());
  }

  @Test
  public void returnsTheHeaderFieldAfterItHasBeenSet() throws Exception {
    request.setHeaderField("Range:");
    Assert.assertEquals("Range:", request.getHeaderField());
  }

  @Test
  public void returnsTheByteRangeAfterItHasBeenSet() throws Exception {
    request.setByteRange("bytes=0-4");
    Assert.assertEquals("bytes=0-4", request.getByteRange());
  }

  @Test
  public void returnsTrueIfRequestIsARootRequest() throws Exception {
    request.setURI("/");
    assertTrue(request.isARootRequest());
  }

  @Test
  public void returnsTrueIfRequestHeaderFieldIsAnAuthorizationRequest() throws Exception {
    request.setBasicRequestStatus(true);
    assertTrue(request.isABasicAuthRequest());
  }

  @Test
  public void returnsBasicAuthCredentials() throws Exception {
    request.setBasicAuthCredentials("Some Credentials");
    Assert.assertEquals("Some Credentials", request.getBasicAuthCredentials());
  }

  @Test
  public void returnsEtagFromRequest() throws Exception {
    request.setEtag("64823rhdsfsaihf");
    Assert.assertEquals("64823rhdsfsaihf", request.getEtag());
  }
}