import java.io.*;
import java.net.Socket;

public class RequestHandler {
  private Socket socket;
  
  public RequestHandler(Socket socket) {
    this.socket = socket;
  }

  protected InputStream getInputStream() {
    InputStream inputStream = null;
    
    try {
      inputStream = socket.getInputStream();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      if (inputStream != null) inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return inputStream;
  }

  protected OutputStream getOutputStream() {
    OutputStream outputStream = null;
    
    try {
      outputStream = socket.getOutputStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    try {
      if (outputStream != null) outputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return outputStream;
  }
}