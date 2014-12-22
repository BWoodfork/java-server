package com.company.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketServer {
    private ResponseHandler responseHandler;
    private ServerSocket serverSocket;
    private ExecutorService executor;
    private boolean running;
    public int connections = 0;

    public SocketServer(int port) throws IOException {
        responseHandler = new ResponseHandler();
        serverSocket = new ServerSocket(port);
        executor = Executors.newFixedThreadPool(25);
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
                                    responseHandler.parseRequest(socket);
                                    responseHandler.sendResponse(socket);
                                    socket.close();
                                } catch (IOException e) {
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