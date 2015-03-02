import com.sun.deploy.util.StringUtils;

import java.util.HashMap;
import java.util.Set;

public class Routes {
//  public static String directory = "../java-server/src/test/me/byronwoodfork/unit-test-directory";
  public static String directory = "../cob_spec/public";
  public static String serverViewsDirectory = "../java-server/default-server-views";
  
  private HashMap<String, HashMap<String, Responder>> routesMap = new HashMap<String, HashMap<String, Responder>>();
  private HashMap<String, Responder> formRouteMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> fileRouteMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> basicAuthRouteMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> optionsMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> redirectMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> rootMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> patchContentMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> partialContentMap = new HashMap<String, Responder>();
  
  private HashMap<String, Responder> getFileRouteMap() {
    fileRouteMap.put("GET", new FileHandler(directory));
    return fileRouteMap;
  }
  
  private HashMap<String, Responder> getRootMap() {
    rootMap.put("GET", new RootHandler(serverViewsDirectory));
    
    return rootMap;
  }
  
  private HashMap<String, Responder> getBasicAuthRouteMap() {
    basicAuthRouteMap.put("GET", new BasicAuthHandler(directory));

    return basicAuthRouteMap;
  }
  
  private HashMap<String, Responder> getFormRouteMap() {
    formRouteMap.put("GET", new FileHandler(directory));
    formRouteMap.put("POST", new FormHandler(directory));
    formRouteMap.put("DELETE", new FormHandler(directory));
    formRouteMap.put("PUT", new FormHandler(directory));
    
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
  
  private HashMap<String, Responder> getPatchContentMap() {
    patchContentMap.put("PATCH", new PatchContentHandler(directory));
    patchContentMap.put("GET", new FileHandler(directory));
    
    return patchContentMap;
  }
  
  private HashMap<String, Responder> getPartialContentmap() {
    partialContentMap.put("GET", new PartialContentHandler(directory));
    
    return partialContentMap;
  }
  
  private HashMap<String, HashMap<String, Responder>> getRoutesMap() {
    routesMap.put("/", getRootMap());
    routesMap.put("file1", getFileRouteMap());
    routesMap.put("file2", getFileRouteMap());
    routesMap.put("image.jpeg", getFileRouteMap());
    routesMap.put("image.png", getFileRouteMap());
    routesMap.put("image.gif", getFileRouteMap());
    routesMap.put("form", getFormRouteMap());
    routesMap.put("text-file.txt", getFileRouteMap());
    routesMap.put("logs", getBasicAuthRouteMap());
    routesMap.put("method_options", getOptionsMap());
    routesMap.put("redirect", getRedirectMap());
    routesMap.put("patch-content.txt", getPatchContentMap());
    routesMap.put("partial_content.txt", getPartialContentmap());

    return routesMap;
  }

  public boolean isAValidMethod(Request request) {
    return isAURIMatch(request) && getRoutesMap().get(request.getURI()).containsKey(request.getHTTPMethod());
  }
  
  public Responder getHandler(Request request) {
    if (isAURIMatch(request) && isAValidMethod(request)) return getRoutesMap().get(request.getURI()).get(request.getHTTPMethod());
    if (isAURIMatch(request) && !isAValidMethod(request)) return new MethodNotAllowedHandler(serverViewsDirectory);
    return new NotFoundHandler(serverViewsDirectory);
  }
  
  private Set<String> getRouteKeySet() {
    return getRoutesMap().keySet();
  }
  
  private boolean isAURIMatch(Request request) {
    for (String uri : getRouteKeySet()) {
      if (request.getURI().equals(uri)) {
        return true;
      }
    }

    return false;
  }

  public String getOptions(Request request) {
    try {
      return StringUtils.join(getRoutesMap().get(request.getURI()).keySet(), ",");
    } catch (NullPointerException e) {
      
      return "GET";
    }
  }
}