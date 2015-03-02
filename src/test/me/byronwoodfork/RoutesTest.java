import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class RoutesTest {
  Request request;
  Routes routes;

  @Before
  public void setUp() throws Exception {
    request = new Request();
    routes = new Routes();
  }

  @Test
  public void returnsTrueIfMethodIsValidOnURI() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");
    
    assertEquals(true, routes.isAValidMethod(request));
  }
  
  @Test
  public void returnsFalseIfMethodIsNotValidOnURI() throws Exception {
    request.setHTTPMethod("POST");
    request.setURI("file1");
    
    assertEquals(false, routes.isAValidMethod(request));
  }
  
  @Test
  public void returnsFalseIfFileIsNotAMatchedFile() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file2222");
    
    assertEquals(false, routes.isAValidMethod(request));
  }
  
  @Test
  public void returnsFileHandlerInstanceWhenEndpointIsValid() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("file1");
    
    assertThat(routes.getHandler(request), instanceOf(FileHandler.class));
  }

  @Test
  public void returnsMethodNotAllowedHandlerWhenMethodOnURIIsNotValid() throws Exception {
    request.setHTTPMethod("POST");
    request.setURI("file1");
    
    assertThat(routes.getHandler(request), instanceOf(MethodNotAllowedHandler.class));
  }
  
  @Test
  public void returnsNotFoundHandlerWhenURIIsNotFound() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("SomeURIThatDoesNotExist");
    
    assertThat(routes.getHandler(request), instanceOf(NotFoundHandler.class));
  }
  
  @Test
  public void returnsRootHandlerWhenRootRequestIsMade() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("/");
    
    assertThat(routes.getHandler(request), instanceOf(RootHandler.class));
  }
  
  @Test
  public void returnsTheMethodOptionsForFile1Path() throws Exception {
    request.setHTTPMethod("OPTIONS");
    request.setURI("file1");

    assertEquals("GET", routes.getOptions(request));
  }

  @Test
  public void returnsTheMethodOptionsForFormPath() throws Exception {
    request.setHTTPMethod("OPTIONS");
    request.setURI("form");

    assertEquals("DELETE,POST,GET,PUT", routes.getOptions(request));
  }
  
  @Test
  public void returnsGETForCobspecRoutes() throws Exception {
    request.setHTTPMethod("GET");
    request.setURI("log");
    
    assertEquals("GET", routes.getOptions(request));
  }
}