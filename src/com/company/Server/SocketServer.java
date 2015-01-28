package com.company.Server;

import com.company.Handler.StreamHandler;
import com.company.request.RequestBuilder;
import com.company.request.Request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketServer {
    private Response response;
    private ServerSocket serverSocket;
    private ExecutorService executor;
    private boolean running;
    public int connections = 0;
    private Request request;
    private StreamHandler streamHandler;
    private RequestBuilder requestBuilder;
    private int port;

    public SocketServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        executor = Executors.newFixedThreadPool(25);
        this.port = port;
    }

    public void start() throws IOException {
        Runnable connectionHandler = new Runnable() {
            public void run() {
                try {
                    while (running) {
                        final Socket socket = serverSocket.accept();
                        getConnections();
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    requestBuilder = new RequestBuilder();
                                    streamHandler = new StreamHandler(requestBuilder);
                                    request = new Request(streamHandler.convertRawRequestToString(socket));
                                    response = new Response(port, request);

                                    request.parseRequest();
                                    response.sendResponse(socket);
                                    socket.close();
                                } catch (Exception e) {
                                    if (executor.isTerminated())
                                      e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        executor.execute(connectionHandler);
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() throws IOException, InterruptedException {
        running = false;
        executor.awaitTermination(50, TimeUnit.MILLISECONDS);
        serverSocket.close();
    }

    public int getConnections() {
        return connections++;
    }
}