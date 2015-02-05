package com.company.Routes;

import com.company.Response.ResponseGenerators.BasicRouteGenerator;
import com.company.Response.ResponseGenerators.DeleteRequestResponseGenerator;
import com.company.Response.ResponseGenerators.PostRequestResponseGenerator;
import com.company.Response.ResponseGenerators.PutRequestResponseGenerator;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.util.HashMap;

public class Routes {
    
    public HashMap<String, RouteInterface> route(StatusBuilder statusBuilder) throws Exception {
        HashMap<String, RouteInterface> storedRoutes = new HashMap<>();
        storedRoutes.put("GET /file1", new BasicRouteGenerator(statusBuilder));
        storedRoutes.put("GET /file2", new BasicRouteGenerator(statusBuilder));
        storedRoutes.put("GET /image.jpeg", new BasicRouteGenerator(statusBuilder));
        storedRoutes.put("GET /image.png", new BasicRouteGenerator(statusBuilder));
        storedRoutes.put("GET /image.gif", new BasicRouteGenerator(statusBuilder));
        storedRoutes.put("GET /form", new PostRequestResponseGenerator(statusBuilder));
        storedRoutes.put("PUT /form", new PutRequestResponseGenerator(statusBuilder));
        storedRoutes.put("DELETE /form", new DeleteRequestResponseGenerator(statusBuilder));
        storedRoutes.put("POST /form", new PostRequestResponseGenerator(statusBuilder));

        return storedRoutes;
    }
    
    public String routeKeys(Request request, StatusBuilder statusBuilder) throws Exception {
        String method = request.getMethod();
        String filePath = request.getFilePath();
        
        for (String key : route(statusBuilder).keySet()) {
            if ((method + " " + filePath).equals(key)) {
                return key;
            }
        }

        return filePath;
    }
}