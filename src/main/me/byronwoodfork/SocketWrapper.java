import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketWrapper {
  private Socket socket;
  
  public SocketWrapper(Socket socket) {
    this.socket = socket;
  }
  
  public InputStream getSocketInputStream() throws IOException {
    return socket.getInputStream();
  }

  public OutputStream getSocketOutputStream() throws IOException {
    return socket.getOutputStream();
  }

  public boolean socketIsClosed() {
    return !socket.isConnected();
  }
}