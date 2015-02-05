package com.company.Response.Routes;

import com.company.Routes.Routes;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoutesTest {
    @Test
    public void GetFile1KeyReturnsFile1ContentsWhenGetBodyIsCalled() throws Exception {
        Routes routes = new Routes();
        StatusBuilder statusBuilder = new StatusBuilder();
        Request request = new Request("GET /file1");
        String file1Body = new String(routes.route(statusBuilder).get("GET /file1").getBody(request));
        
        assertEquals("file1 contents", file1Body);
    }
    
    @Test
    public void GetFile2KeyReturnsFile2Contents() throws Exception {
        Routes routes = new Routes();
        StatusBuilder statusBuilder = new StatusBuilder();
        Request request = new Request("GET /file2");
        String file2Body = new String(routes.route(statusBuilder).get("GET /file2").getBody(request));
        
        assertEquals("file2 contents", file2Body);
    }
    
    @Test
    public void returnsTheRouteKeyIfTheRequestedEndpointMatchesAKeyInRoute() throws Exception {
        Routes routes = new Routes();
        StatusBuilder statusBuilder = new StatusBuilder();
        Request request = new Request("GET /file1");
        
        assertEquals("GET /file1", routes.routeKeys(request, statusBuilder));
    }
    
    @Test
    public void returnsTheRouteKeyForFile2() throws Exception {
        Routes routes = new Routes();
        StatusBuilder statusBuilder = new StatusBuilder();
        Request request = new Request("GET /file2");
        
        assertEquals("GET /file2", routes.routeKeys(request, statusBuilder));
    }
    
    @Test
    public void returnsTheEndPointNameIfTheRequestedEndpointDoesNotMatchAKeyInRoute() throws Exception {
        Routes routes = new Routes();
        StatusBuilder statusBuilder = new StatusBuilder();
        Request request = new Request("GET /file22");

        assertEquals("/file22", routes.routeKeys(request, statusBuilder));
    }
}