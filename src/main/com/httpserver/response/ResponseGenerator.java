package com.httpserver.response;

import com.httpserver.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class ResponseGenerator {
  private Response response;
  private ExecutorService pool;
  private int port;

  public ResponseGenerator(ExecutorService pool, Response response, int port) {
    this.pool = pool;
    this.response = response;
    this.port = port;
  }
  
  public void start() throws IOException {
    ServerSocket serverSocket = new ServerSocket(port);

    while (true) {
      try {
        RequestHandler requestHandler = new RequestHandler(serverSocket.accept());
        pool.execute(new ConnectionHandler(requestHandler, response));
      } catch (IOException e) {
       e.printStackTrace();
      }
    }
  }
}