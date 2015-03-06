package com.httpserver.response;

import java.util.HashMap;
import java.util.Set;

public class HTTPStatusCodes {
  private int status;
  protected static HashMap<Integer, String> statusCodesMap = new HashMap<Integer, String>();
  
  public static HashMap<Integer, String> getStatusCodesMap() {
    statusCodesMap.put(200, "200 OK");
    statusCodesMap.put(204, "204 No Content");
    statusCodesMap.put(206, "206 Partial Content");
    statusCodesMap.put(300, "300 Multiple Choices");
    statusCodesMap.put(301, "301 Moved Permanently");
    statusCodesMap.put(302, "302 Found");
    statusCodesMap.put(304, "304 Not Modified");
    statusCodesMap.put(307, "307 Temporary Redirect");
    statusCodesMap.put(400, "400 Bad Request");
    statusCodesMap.put(401, "401 Unauthorized");
    statusCodesMap.put(403, "403 Forbidden");
    statusCodesMap.put(404, "404 Not Found");
    statusCodesMap.put(405, "405 Method Not Allowed");
    statusCodesMap.put(410, "410 Gone");
    statusCodesMap.put(500, "500 Internal Server Error");
    statusCodesMap.put(501, "501 Not Implemented");
    statusCodesMap.put(503, "503 Service Unavailable");
    statusCodesMap.put(550, "550 Permission Denied");
    
    return statusCodesMap;
  }

  public void setStatus(Integer status) throws Exception {
    isAValidKey(status);
    this.status = status;
  }

  public String getStatus() {
    return getStatusCodesMap().get(status);
  }

  public String getFormattedStatusMessage() {
    String newLine = "\r\n";
    return "HTTP/1.1 " + getStatus() + newLine;
  }

  private Set<Integer> getKeySet() {
    return getStatusCodesMap().keySet();
  }

  boolean isAValidKey(Integer key) throws Exception {
    for (Integer code : getKeySet()) {
      if (key.equals(code))
        return true;
    }
    
    throw new Exception("Status Code Invalid");
  }
}