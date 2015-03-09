package com.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) throws IOException {
    ArgsParser argsParser = new ArgsParser(args);
    int port = argsParser.getPort();
    String directory = argsParser.getDirectory();
    ExecutorService pool = Executors.newFixedThreadPool(5);
    ServerSocket serverSocket = new ServerSocket(port);
    Server server = new Server(serverSocket, pool, port, directory);
    server.start();
  }
}