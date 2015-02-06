package com.company.Response;

import com.company.Routes.Routes;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

public class Body {
    private Routes routes;

    public Body() {
        routes = new Routes();
    }
    
    public byte[] getBody(StatusBuilder statusBuilder, Request request) throws Exception {
//        if method is a GET method, then call .getBody on it. If it isn't, don't.
//        if method is a POST, PUT or DELETE method, then call .execute on it
        
//      Have this call .getDate .getBlah on on objects that get returned to give me the full response
        
        String routeKey = routes.routeKeys(request, statusBuilder);
        
        if (routes.getRoutes(statusBuilder).get(routeKey) != null) {
            return routes.getRoutes(statusBuilder).get(routeKey).getBody(request);
        } else 
            statusBuilder.setHTTPStatus(404);
        return "this is not the page you are looking for".getBytes();
    }
}