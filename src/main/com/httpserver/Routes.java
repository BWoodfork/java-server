package com.httpserver;

import com.httpserver.request.Request;
import com.httpserver.response.*;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class Routes {
  private String directory;
  public static String serverViewsDirectory = "../java-server/default-server-views";
  
  public Routes(String directory) {
    this.directory = directory;
  }

  private HashMap<String, HashMap<String, Responder>> routesMap = new HashMap<String, HashMap<String, Responder>>();
  private HashMap<String, Responder> formRouteMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> fileRouteMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> basicAuthRouteMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> optionsMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> redirectMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> rootMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> patchContentMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> partialContentMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> parameterMap = new HashMap<String, Responder>();

  private HashMap<String, Responder> getFileRouteMap(Request request) {
    fileRouteMap.put("GET", new FileHandler(directory, request.getURI()));
    return fileRouteMap;
  }
  
  private HashMap<String, Responder> getRootMap() {
    rootMap.put("GET", new RootHandler(serverViewsDirectory));
    
    return rootMap;
  }
  
  private HashMap<String, Responder> getBasicAuthRouteMap(Request request) {
    basicAuthRouteMap.put("GET", new BasicAuthHandler(directory, request.getURI(), request.getBasicAuthCredentials()));

    return basicAuthRouteMap;
  }
  
  private HashMap<String, Responder> getFormRouteMap(Request request) {
    formRouteMap.put("GET", new FileHandler(directory, request.getURI()));
    formRouteMap.put("POST", new FormHandler(directory, request.getHTTPMethod(), request.getURI()));
    formRouteMap.put("DELETE", new FormHandler(directory, request.getHTTPMethod(), request.getURI()));
    formRouteMap.put("PUT", new FormHandler(directory, request.getHTTPMethod(), request.getURI()));
    
    return formRouteMap;
  }
  
  private HashMap<String, Responder> getOptionsMap() {
    optionsMap.put("OPTIONS", new MethodOptionsHandler());
    optionsMap.put("GET", new MethodOptionsHandler());
    optionsMap.put("PUT", new MethodOptionsHandler());
    optionsMap.put("HEAD", new MethodOptionsHandler());
    optionsMap.put("POST", new MethodOptionsHandler());
    
    return optionsMap;
  }
  
  private HashMap<String, Responder> getRedirectMap() {
    redirectMap.put("GET", new RedirectHandler());
    
    return redirectMap;
  }
  
  private HashMap<String, Responder> getPatchContentMap(Request request) {
    patchContentMap.put("PATCH", new PatchContentHandler(directory, request.getURI(), request.getEtag()));
    patchContentMap.put("GET", new FileHandler(directory, request.getURI()));
    
    return patchContentMap;
  }
  
  private HashMap<String, Responder> getPartialContentmap(Request request) {
    partialContentMap.put("GET", new PartialContentHandler(directory, request.getURI() ,request.getByteRange()));
    
    return partialContentMap;
  }
  
  private HashMap<String, Responder> getParameterMap(Request request) {
    parameterMap.put("GET", new ParameterHandler(request.getParameterValues()));
    
    return parameterMap;
  }
  
  private HashMap<String, HashMap<String, Responder>> getRoutesMap(Request request) {
    routesMap.put("/", getRootMap());
    routesMap.put("file1", getFileRouteMap(request));
    routesMap.put("file2", getFileRouteMap(request));
    routesMap.put("image.jpeg", getFileRouteMap(request));
    routesMap.put("image.png", getFileRouteMap(request));
    routesMap.put("image.gif", getFileRouteMap(request));
    routesMap.put("form", getFormRouteMap(request));
    routesMap.put("text-file.txt", getFileRouteMap(request));
    routesMap.put("logs", getBasicAuthRouteMap(request));
    routesMap.put("method_options", getOptionsMap());
    routesMap.put("redirect", getRedirectMap());
    routesMap.put("patch-content.txt", getPatchContentMap(request));
    routesMap.put("partial_content.txt", getPartialContentmap(request));
    routesMap.put("parameters", getParameterMap(request));

    return routesMap;
  }
  
  public Responder getHandler(Request request) {
    if (!isAURIMatch(request) && isADirectoryFileMatch(getDirectoryFileNames(), request)) return new FileHandler(directory, request.getURI());
    if (isAURIMatch(request) && isAValidMethod(request)) return getRoutesMap(request).get(request.getURI()).get(request.getHTTPMethod());
    if (isAURIMatch(request) && !isAValidMethod(request)) return new MethodNotAllowedHandler(serverViewsDirectory);
    return new NotFoundHandler(serverViewsDirectory);
  }
  
  private Set<String> getRouteKeySet(Request request) {
    return getRoutesMap(request).keySet();
  }

  public boolean isAValidMethod(Request request) {
    return isAURIMatch(request) && getRoutesMap(request).get(request.getURI()).containsKey(request.getHTTPMethod());
  }
  
  public String getOptions(Request request) {
    try {
      return String.join(",", getRoutesMap(request).get(request.getURI()).keySet());
    } catch (NullPointerException e) {
      
      return "GET";
    }
  }
  
  private boolean isAURIMatch(Request request) {
    for (String uri : getRouteKeySet(request)) {
      if (request.getURI().equals(uri)) {
        return true;
      }
    }

    return false;
  }
  
  public String[] getDirectoryFileNames() {
    File file = new File(directory);
    return file.list();
  }

  public boolean isADirectoryFileMatch(String[] fileList, Request request) {
    String URI = request.getURI();
    for (String file : fileList) {
      if (file.equals(URI)) return true;
    }
    
    return false;
  }
}