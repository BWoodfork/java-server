package com.httpserver.response;

import com.httpserver.request.Request;
import com.httpserver.request.RequestHandler;
import com.httpserver.request.RequestParser;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConnectionHandler implements Runnable {
  private RequestHandler requestHandler;
  private Response response;

  public ConnectionHandler(RequestHandler requestHandler, Response response) {
    this.requestHandler = requestHandler;
    this.response = response;
  }

  @Override
  public void run() {
    InputStream inputStream = requestHandler.getInputStream();
    RequestParser requestParser = new RequestParser(inputStream);
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