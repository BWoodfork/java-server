import java.util.concurrent.ExecutorService;

public class ResponseGenerator {
  private ExecutorService executorService;
  private int requests = 0;
  private RequestHandler requestHandler;
  private Response response;

  public ResponseGenerator(ExecutorService executorService, RequestHandler requestHandler) {
    this.executorService = executorService;
    this.requestHandler = requestHandler;
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    response = new Response(httpStatusCodes);
  }

  public void start() {
    requests++;
    executorService.execute(new ConnectionHandler(requestHandler, response));
  }

  protected int getRequests() {
    return requests;
  }

  public String getRequest() {
    return null;
  }
}