package main.com.mycompany.Routes;

import main.com.mycompany.Response.ResponseBuilders.*;
import main.com.mycompany.Utilities.FileMatcher;
import main.com.mycompany.request.Request;

import java.util.HashMap;

public class Routes {
    private FileMatcher fileMatcher;

    public Routes(FileMatcher fileMatcher) {
        this.fileMatcher = fileMatcher;
    }

    public HashMap<String, ResponseBuilder> getRoutes(StatusBuilder statusBuilder) throws Exception {
        HashMap<String, ResponseBuilder> storedRoutes = new HashMap<>();
        String parameterPath = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        
        storedRoutes.put("GET /", new IndexResponseBuilder(statusBuilder));
        storedRoutes.put("GET /redirect", new RedirectResponseBuilder(statusBuilder));
        storedRoutes.put("GET /file1", new BasicResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("GET /file2", new BasicResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("GET /image.jpeg", new BasicResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("GET /image.png", new BasicResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("GET /image.gif", new BasicResponseBuilder(statusBuilder, fileMatcher));

        storedRoutes.put("GET /form", new BasicResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("PUT /form", new BasicResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("DELETE /form", new BasicResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("POST /form", new BasicResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("GET /patch-content.txt", new BasicResponseBuilder(statusBuilder, fileMatcher));

        storedRoutes.put("GET /logs", new BasicAuthenticationResponseBuilder(statusBuilder));
        storedRoutes.put("PATCH /patch-content.txt", new PatchContentResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("GET /partial_content.txt", new PartialContentResponseBuilder(statusBuilder, fileMatcher));
        storedRoutes.put("GET " + parameterPath, new ParameterResponseBuilder(statusBuilder));
        storedRoutes.put("OPTIONS /method_options", new OptionsResponseBuilder(statusBuilder));

        return storedRoutes;
    }

    public String routeKeys(Request request, StatusBuilder statusBuilder) throws Exception {
        String method = request.getMethod();
        String filePath = request.getFilePath();
        
        for (String key : getRoutes(statusBuilder).keySet()) {
            if ((method + " " + filePath).equals(key)) {
                return key;
            }
        }

        return filePath;
    }
    
    public ResponseBuilder getRequestedRoute(Request request, StatusBuilder statusBuilder) throws Exception {
        return getRoutes(statusBuilder).get(routeKeys(request, statusBuilder));
    }
}