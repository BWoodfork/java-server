package com.httpserver;

import com.httpserver.response.Server;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) throws IOException {
    String directory = "../cob_spec/public";
    ExecutorService pool = Executors.newFixedThreadPool(5);
    int port = 5000;
    Server server = new Server(pool, port, directory);
    server.start();
  }
}