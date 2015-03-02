import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConnectionHandler implements Runnable {
  private RequestHandler requestHandler;
  private Response response;

  public ConnectionHandler(RequestHandler requestHandler, Response response) {
    this.requestHandler = requestHandler;
    this.response = response;
  }

  @Override
  public void run() {
    InputStream inputStream = requestHandler.getInputStream();
    RequestParser requestParser = new RequestParser(inputStream);
    try {
      Request request = requestParser.parse();
      generateResponse(request);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void generateResponse(Request request) throws IOException {
    DataOutputStream dataOutputStream = new DataOutputStream(requestHandler.getOutputStream());
    
    byte[] body = response.getHTTPMessageBody(request);
    dataOutputStream.write(response.getHTTPStatusMessage());
    dataOutputStream.write(response.getContentType(request));
    dataOutputStream.write(response.getOptions());
    dataOutputStream.write(response.getContentLength(request));
    dataOutputStream.write(body);
    dataOutputStream.flush();
  }
}