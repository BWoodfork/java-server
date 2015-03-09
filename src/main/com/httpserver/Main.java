package com.httpserver;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) throws IOException {
    ArgsParser argsParser = new ArgsParser(args);
    int port = argsParser.getPort();
    String directory = argsParser.getDirectory();
    ExecutorService pool = Executors.newFixedThreadPool(5);
    Server server = new Server(pool, port, directory);
    server.start();
  }
}