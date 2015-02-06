package com.company.Routes;

import com.company.Response.ResponseGenerators.*;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.util.HashMap;

public class Routes {
    
    public HashMap<String, ResponseInterface> getRoutes(StatusBuilder statusBuilder) throws Exception {
        HashMap<String, ResponseInterface> storedRoutes = new HashMap<>();
        storedRoutes.put("GET /", new IndexResponseGenerator(statusBuilder));
        storedRoutes.put("GET /redirect", new RedirectResponseGenerator(statusBuilder));
        storedRoutes.put("GET /file1", new BasicResponseGenerator(statusBuilder));
        storedRoutes.put("GET /file2", new BasicResponseGenerator(statusBuilder));
        storedRoutes.put("GET /image.jpeg", new BasicResponseGenerator(statusBuilder));
        storedRoutes.put("GET /image.png", new BasicResponseGenerator(statusBuilder));
        storedRoutes.put("GET /image.gif", new BasicResponseGenerator(statusBuilder));
//        make get /form return basicresponsegenerator
        storedRoutes.put("GET /form", new PostRequestResponseGenerator(statusBuilder));
        storedRoutes.put("PUT /form", new PutRequestResponseGenerator(statusBuilder));
        storedRoutes.put("DELETE /form", new DeleteRequestResponseGenerator(statusBuilder));
        storedRoutes.put("POST /form", new PostRequestResponseGenerator(statusBuilder));
        storedRoutes.put("GET /logs", new BasicAuthenticationResponseGenerator(statusBuilder));

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
}