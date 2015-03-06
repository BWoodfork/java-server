package com.httpserver;

import com.httpserver.response.Server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) throws IOException {
    String directory = args[0];
    int port = Integer.parseInt(args[1]);
    System.out.println("Server Is Listening on Port: " + port);
    ExecutorService pool = Executors.newFixedThreadPool(5);
    ArgsParser argsParser = new ArgsParser(port, directory);
    Server server = new Server(pool, argsParser.getPort(), argsParser.getDirectory());
    server.start();
  }
}