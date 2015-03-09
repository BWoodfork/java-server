package com.httpserver;

import com.httpserver.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class Server {
  private ExecutorService pool;
  private int port;
  private String directory;
  public int requestCount;
  private ServerSocket serverSocket;

  public Server(ServerSocket serverSocket, ExecutorService pool, int port, String directory) {
    this.serverSocket = serverSocket;
    this.pool = pool;
    this.port = port;
    this.directory = directory;
  }
  
  public void start() throws IOException {
    System.out.println("Server Is Listening on port: " + port);

    while (true) {
      try {
        RequestHandler requestHandler = new RequestHandler(serverSocket.accept());
        pool.execute(new ConnectionHandler(requestHandler, port, directory));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}