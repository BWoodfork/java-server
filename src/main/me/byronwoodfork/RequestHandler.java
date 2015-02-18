import java.io.*;
import java.net.Socket;

public class RequestHandler {
  private Socket socket;
  
  public RequestHandler(Socket socket) {
    this.socket = socket;
  }

  public InputStream getInputStream() {
    InputStream inputStream = null;
    
    try {
      inputStream = socket.getInputStream();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      if (inputStream != null) inputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return inputStream;
  }

  public OutputStream getOutputStream() {
    OutputStream outputStream = null;
    
    try {
      outputStream = socket.getOutputStream();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    try {
      if (outputStream != null) outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return outputStream;
  }

  public String convertRequestToString() throws Exception {
    InputStreamReader inputStreamReader = new InputStreamReader(getInputStream());
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    
    StringBuilder requestBuilder = new StringBuilder();
    String line;
    
    do {
      line = bufferedReader.readLine();
      requestBuilder.append(line);
      if (line.equals("")) break;
    } while (bufferedReader.ready());
    
    return requestBuilder.toString();
  }
}