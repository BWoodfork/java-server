package main.com.mycompany.Response.Routes;

import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.Response.ResponseBuilders.StatusBuilder;
import main.com.mycompany.Routes.Routes;
import main.com.mycompany.Utilities.FileMatcher;
import main.com.mycompany.request.Request;
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
    
//    @Test
//    public void returnsTheEndPointNameIfTheRequestedEndpointDoesNotMatchAKeyInRoute() throws Exception {
//        Request request = new Request("GET /file22");
//
//        assertEquals("/file22", routes.routeKeys(request, statusBuilder));
//    }
    
//    @Test
//    public void returnsTrueIfRouteExists() throws Exception {
//        Request request = new Request("GET /");
//        String route = "/";
//
//        assertEquals(true, routes.isAnExisitingRoute(route, request, statusBuilder));
//    }
    
//    @Test
//    public void returnsFalseIfRouteDoesNotExist() throws Exception {
//        Request request = new Request("GET /88989798");
//        String route = "/88989798";
//
//        assertEquals(false, routes.isAnExisitingRoute(route, request, statusBuilder));
//    }
    
//    @Test
//    public void returnsFilePathsFromGetRoutes() throws Exception {
//        Request request = new Request("GET /file1");
//        
//        String[]
        
//        assertEquals("/file1", routes.getKeySetFilePaths(statusBuilder));
//    }
    
//    @Test
//    public void returnsTrueIfMethodCallIsAllowedOnRoutes() throws Exception {
//        Request request = new Request("GET /file1");
//        String route = "/file1";
//        String method = "GET";
//
//        assertEquals(true, routes.isAnAllowedMethod(request));
//    }
//
//    @Test
//    public void returnsFalseIfMethodCallIsNotAllowedOnRoute() throws Exception {
//        Request request = new Request("POST /file1");
//
//        assertEquals(false, routes.isAnAllowedMethod(request));
//    }
    
//    @Test
//    public void returnsTheFilePathsThatAreAvailableInRoutes() throws Exception {
//        Request request = new Request("GET /");
//
//        String[] array = {""};
//        assertEquals(array, routes.buildFilePathsFromRoutes(statusBuilder));
//    }
//    
//    @Test
//    public void returnsTrueIfTheRequestedPathIsValid() throws Exception {
//        Request request = new Request("GET /");
//
//        assertEquals(true, routes.isAValidPath(request, statusBuilder));
//    }
//
//    @Test
//    public void returnsFalseIfRequestedPathIsNotValid() throws Exception {
//        Request request = new Request("GET /FFOOOOODDD");
//
//        assertEquals(false, routes.isAValidPath(request, statusBuilder));
//    }
    
//    @Test
//    public void returnsTrueIfTheRequestedRouteExists() throws Exception {
//        Request request = new Request("GET /file1");
//
//        assertEquals(true, routes.filePathExists(request, statusBuilder));
//    }
//
//    @Test
//    public void returnsFalseIfTheRequestedRouteDoesNotExist() throws Exception {
//        Request request = new Request("GET /file222222");
//
//        assertEquals(false, routes.filePathExists(request, statusBuilder));
//    }
//
//    @Test
//    public void returnsTrueIfRequestedMethodOnTheFilePathExists() throws Exception {
//        Request request = new Request("GET /file1");
//
//        assertEquals(true, routes.methodExists(request, statusBuilder));
//    }
//
//    @Test
//    public void returnsFalseIfRequestedMethodOnTheFilePathDoesNotExist() throws Exception {
//        Request request = new Request("GET /file2222222");
//
//        assertEquals(false, routes.methodExists(request, statusBuilder));
//    }
//
//    @Test
//    public void returnsFalseIfTheRequestedRouteAndMethodDontExist() throws Exception {
//        Request request = new Request("PUT /file1");
//
//        assertEquals(false, routes.requestedComboExists(request, statusBuilder));
//    }
//
//    @Test
//    public void returnsTrueIfTheRequestedRouteAndMethodExist() throws Exception {
//        Request request = new Request("GET /file1");
//
//        assertEquals(true, routes.requestedComboExists(request, statusBuilder));
//    }
}