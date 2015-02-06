package com.company.Routes;

import com.company.Response.ResponseGenerators.*;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.util.HashMap;

public class Routes {
    
    public HashMap<String, IResponse> getRoutes(StatusBuilder statusBuilder) throws Exception {
        HashMap<String, IResponse> storedRoutes = new HashMap<>();
        storedRoutes.put("GET /", new IndexIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /redirect", new RedirectIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /file1", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /file2", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /image.jpeg", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /image.png", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /image.gif", new BasicIResponseGenerator(statusBuilder));
//        make get /form return basicresponsegenerator
        storedRoutes.put("GET /form", new PostRequestIResponseGenerator(statusBuilder));
        storedRoutes.put("PUT /form", new PutRequestIResponseGenerator(statusBuilder));
        storedRoutes.put("DELETE /form", new DeleteRequestIResponseGenerator(statusBuilder));
        storedRoutes.put("POST /form", new PostRequestIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /logs", new BasicAuthenticationIResponseGenerator(statusBuilder));

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