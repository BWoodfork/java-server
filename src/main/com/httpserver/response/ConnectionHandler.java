package com.httpserver.response;

import com.httpserver.Routes;
import com.httpserver.request.Request;
import com.httpserver.request.RequestHandler;
import com.httpserver.request.RequestParser;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConnectionHandler implements Runnable {
  private RequestHandler requestHandler;
  private String directory;
  private int port;

  public ConnectionHandler(RequestHandler requestHandler, int port, String directory) {
    this.requestHandler = requestHandler;
    this.port = port;
    this.directory = directory;
  }

  @Override
  public void run() {
    InputStream inputStream = requestHandler.getInputStream();
    RequestParser requestParser = new RequestParser(inputStream);
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    Routes routes = new Routes(directory);
    Response response = new Response(httpStatusCodes, routes, port);
    
      try {
        Request request = requestParser.parse();
        System.out.println(request.getFullRequest());
        DataOutputStream dataOutputStream = new DataOutputStream(requestHandler.getOutputStream());
        dataOutputStream.write(response.formatResponse(request));
        dataOutputStream.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }

    requestHandler.closeSocket();
  }
}