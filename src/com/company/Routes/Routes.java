package com.company.Routes;

import com.company.Response.ResponseGenerators.*;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.util.HashMap;

public class Routes {
    
    public HashMap<String, IResponse> getRoutes(StatusBuilder statusBuilder) throws Exception {
        HashMap<String, IResponse> storedRoutes = new HashMap<>();
        String parameterPath = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        
        storedRoutes.put("GET /", new IndexIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /redirect", new RedirectIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /file1", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /file2", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /image.jpeg", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /image.png", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /image.gif", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /form", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("PUT /form", new PutRequestIResponseGenerator(statusBuilder));
        storedRoutes.put("DELETE /form", new DeleteRequestIResponseGenerator(statusBuilder));
        storedRoutes.put("POST /form", new PostRequestIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /logs", new BasicAuthenticationIResponseGenerator(statusBuilder));
        storedRoutes.put("PATCH /patch-content.txt", new PatchContentResponseGenerator(statusBuilder));
        storedRoutes.put("GET /patch-content.txt", new BasicIResponseGenerator(statusBuilder));
        storedRoutes.put("GET /partial_content.txt", new PartialContentResponseGenerator(statusBuilder));
        storedRoutes.put("GET " + parameterPath, new ParameterIResponseGenerator(statusBuilder));
        
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