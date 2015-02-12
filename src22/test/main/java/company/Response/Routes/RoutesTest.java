package main.java.company.Response.Routes;

import main.java.company.Response.Headers.Options;
import main.java.company.Routes.Routes;
import main.java.company.Utilities.FileMatcher;
import main.java.company.Response.ResponseBuilders.StatusBuilder;
import main.java.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoutesTest {
    private Routes routes;
    private StatusBuilder statusBuilder;
    private FileMatcher fileMatcher;

    @Before
    public void setUp() throws Exception {
        fileMatcher = new FileMatcher();
        routes = new Routes(fileMatcher);
        statusBuilder = new StatusBuilder();
    }
    
    @Test
    public void GetFile1KeyReturnsFile1ContentsWhenGetBodyIsCalled() throws Exception {
        Request request = new Request("GET /file1");
        Options options = new Options();
        String file1Body = new String(routes.getRoutes(statusBuilder).get("GET /file1").buildResponse(request, options));
        
        assertEquals("file1 contents", file1Body);
    }
    
    @Test
    public void GetFile2KeyReturnsFile2Contents() throws Exception {
        Request request = new Request("GET /file2");
        Options options = new Options();
        String file2Body = new String(routes.getRoutes(statusBuilder).get("GET /file2").buildResponse(request, options));
        
        assertEquals("file2 contents", file2Body);
    }
    
    @Test
    public void returnsTheRouteKeyIfTheRequestedEndpointMatchesAKeyInRoute() throws Exception {
        Request request = new Request("GET /file1");
        
        assertEquals("GET /file1", routes.routeKeys(request, statusBuilder));
    }
    
    @Test
    public void returnsTheRouteKeyForFile2() throws Exception {
        Request request = new Request("GET /file2");
        
        assertEquals("GET /file2", routes.routeKeys(request, statusBuilder));
    }
    
    @Test
    public void returnsTheEndPointNameIfTheRequestedEndpointDoesNotMatchAKeyInRoute() throws Exception {
        Request request = new Request("GET /file22");

        assertEquals("/file22", routes.routeKeys(request, statusBuilder));
    }
}