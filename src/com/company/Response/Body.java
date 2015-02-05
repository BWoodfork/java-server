package com.company.Response;

import com.company.Routes.Routes;
import com.company.Utilities.FileMatcher;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

public class Body {
    private FilePaths filePaths;
    private Routes routes;

    public Body() {
        FileMatcher fileMatcher = new FileMatcher();
        filePaths = new FilePaths(fileMatcher);
        routes = new Routes();
    }
    
    public byte[] getBody(StatusBuilder statusBuilder, Request request) throws Exception {
        String routeKey = routes.routeKeys(request, statusBuilder);
        
        if (routes.route(statusBuilder).get(routeKey) != null) {
            return routes.route(statusBuilder).get(routeKey).getBody(request);
        } else 
        
            statusBuilder.setHTTPStatus(404);
        return "this is not the page you are looking for".getBytes();
    }
}