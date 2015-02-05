package com.company.Routes;

import com.company.Handler.BasicFileHandler;
import com.company.Handler.PostRequestHandler;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.util.HashMap;

public class Routes {
    
    public HashMap<String, RouteInterface> route(StatusBuilder statusBuilder) throws Exception {
        HashMap<String, RouteInterface> storedRoutes = new HashMap<>();
        storedRoutes.put("GET /file1", new BasicFileHandler(statusBuilder));
        storedRoutes.put("GET /file2", new BasicFileHandler(statusBuilder));
        storedRoutes.put("GET /image.jpeg", new BasicFileHandler(statusBuilder));
        storedRoutes.put("GET /image.png", new BasicFileHandler(statusBuilder));
        storedRoutes.put("GET /image.gif", new BasicFileHandler(statusBuilder));
        storedRoutes.put("GET /form", new PostRequestHandler());
        storedRoutes.put("PUT /form", new PostRequestHandler());
        storedRoutes.put("DELETE /form", new PostRequestHandler());
        storedRoutes.put("POST /form", new PostRequestHandler());

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