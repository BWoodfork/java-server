package com.httpserver;

import com.httpserver.request.Request;
import com.httpserver.response.*;
import com.httpserver.response.Responders.*;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class Routes {
  private String directory;

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
    fileRouteMap.put("GET", new FileResponder(directory, request.getURI()));
    return fileRouteMap;
  }
  
  private HashMap<String, Responder> getRootMap() {
    rootMap.put("GET", new RootResponder(Constants.SERVER_VIEWS_DIRECTORY));
    
    return rootMap;
  }
  
  private HashMap<String, Responder> getBasicAuthRouteMap(Request request) {
    basicAuthRouteMap.put("GET", new BasicAuthResponder(directory, request.getURI(), request.getBasicAuthCredentials()));

    return basicAuthRouteMap;
  }
  
  private HashMap<String, Responder> getFormRouteMap(Request request) {
    formRouteMap.put("GET", new FileResponder(directory, request.getURI()));
    formRouteMap.put("POST", new FormResponder(directory, request.getHTTPMethod(), request.getURI()));
    formRouteMap.put("DELETE", new FormResponder(directory, request.getHTTPMethod(), request.getURI()));
    formRouteMap.put("PUT", new FormResponder(directory, request.getHTTPMethod(), request.getURI()));
    
    return formRouteMap;
  }
  
  private HashMap<String, Responder> getOptionsMap() {
    optionsMap.put("OPTIONS", new MethodOptionsResponder());
    optionsMap.put("GET", new MethodOptionsResponder());
    optionsMap.put("PUT", new MethodOptionsResponder());
    optionsMap.put("HEAD", new MethodOptionsResponder());
    optionsMap.put("POST", new MethodOptionsResponder());
    
    return optionsMap;
  }
  
  private HashMap<String, Responder> getRedirectMap() {
    redirectMap.put("GET", new RedirectResponder());
    
    return redirectMap;
  }
  
  private HashMap<String, Responder> getPatchContentMap(Request request) {
    patchContentMap.put("PATCH", new PatchContentResponder(directory, request.getURI(), request.getEtag()));
    patchContentMap.put("GET", new FileResponder(directory, request.getURI()));
    
    return patchContentMap;
  }
  
  private HashMap<String, Responder> getPartialContentmap(Request request) {
    partialContentMap.put("GET", new PartialContentResponder(directory, request.getURI() ,request.getByteRange()));
    
    return partialContentMap;
  }
  
  private HashMap<String, Responder> getParameterMap(Request request) {
    parameterMap.put("GET", new ParameterResponder(request.getParameterValues()));
    
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
    if (!isAURIMatch(request) && isADirectoryFileMatch(getDirectoryFileNames(), request)) return new FileResponder(directory, request.getURI());
    if (isAURIMatch(request) && isAValidMethod(request)) return getRoutesMap(request).get(request.getURI()).get(request.getHTTPMethod());
    if (isAURIMatch(request) && !isAValidMethod(request)) return new MethodNotAllowedResponder(Constants.SERVER_VIEWS_DIRECTORY);
    return new NotFoundResponder(Constants.SERVER_VIEWS_DIRECTORY);
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