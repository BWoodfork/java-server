import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class ResponseGenerator implements Runnable {
  private ExecutorService executorService;
  private int requests = 0;
  private RequestHandler requestHandler;
  private HTTPStatusCodes httpStatusCodes;

  public ResponseGenerator(ExecutorService executorService, RequestHandler requestHandler) {
    this.executorService = executorService;
    this.requestHandler = requestHandler;
    httpStatusCodes = new HTTPStatusCodes();
  }

  public void start() {

  }

  public void stop() {

  }
  
  @Override
  public void run() {
    
  }

  protected int getRequests() {
    return requests;
  }
  
  public void generateResponse(Request request) throws IOException {
    Response response = new Response();
    DataOutputStream dataOutputStream = new DataOutputStream(requestHandler.getOutputStream());
    dataOutputStream.write(response.getHTTPStatusMessage(httpStatusCodes));
    dataOutputStream.write(response.getContentType(request, httpStatusCodes));
    dataOutputStream.write(response.getOptions());
    dataOutputStream.write(response.getContentLength(request, httpStatusCodes));
    dataOutputStream.write(response.getHTTPMessageBody(request, httpStatusCodes));
    dataOutputStream.flush();
  }
}