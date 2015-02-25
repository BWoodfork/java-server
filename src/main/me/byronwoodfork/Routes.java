import java.util.HashMap;
import java.util.Set;

public class Routes {
  public static String directory = "../java-server/temp-directory";
  public static String serverViewsDirectory = "../java-server/default-server-views";
  
  private HashMap<String, HashMap<String, Responder>> routesMap = new HashMap<String, HashMap<String, Responder>>();
  private HashMap<String, Responder> formRouteMap = new HashMap<String, Responder>();
  private HashMap<String, Responder> fileRouteMap = new HashMap<String, Responder>();

  private HashMap<String, Responder> getFileRouteMap() {
    fileRouteMap.put("GET", new FileHandler(directory));
    return fileRouteMap;
  }
  
  private HashMap<String, Responder> getFormRouteMap() {
    formRouteMap.put("GET", new FormHandler(directory));
    formRouteMap.put("POST", new FormHandler(directory));
    formRouteMap.put("GET", new FormHandler(directory));
    formRouteMap.put("DELETE", new FormHandler(directory));
    
    return formRouteMap;
  }
  
  private HashMap<String, HashMap<String, Responder>> getRoutesMap() {
    routesMap.put("file1", getFileRouteMap());
    routesMap.put("file2", getFileRouteMap());
    routesMap.put("image.jpeg", getFileRouteMap());
    routesMap.put("image.png", getFileRouteMap());
    routesMap.put("image.gif", getFileRouteMap());
    routesMap.put("form", getFormRouteMap());
    
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
}