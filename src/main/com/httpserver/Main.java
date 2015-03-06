package com.httpserver;

import com.httpserver.response.ResponseGenerator;
import com.httpserver.response.HTTPStatusCodes;
import com.httpserver.response.Response;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) throws IOException {
    String directory = "../cob_spec/public";
    ExecutorService pool = Executors.newCachedThreadPool();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    Routes routes = new Routes(directory);
    int port = 5000;
    Response response = new Response(httpStatusCodes, routes, port);
    ResponseGenerator responseGenerator = new ResponseGenerator(pool, response, port);
    responseGenerator.start();
  }
}