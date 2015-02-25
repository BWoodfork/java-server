import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(5000);
    Socket socket = serverSocket.accept();
    RequestHandler requestHandler = new RequestHandler(socket);
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    ResponseGenerator responseGenerator = new ResponseGenerator(executorService, requestHandler);
    responseGenerator.run();
  }
}