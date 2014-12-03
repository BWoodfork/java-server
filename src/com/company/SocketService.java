package com.company;

import com.company.HeaderData.HeaderOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {
    private HeaderOutput headerOutput;

    public SocketService() {
        headerOutput = new HeaderOutput();
    }

    public void serve() throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        try {
            while(true) {
                Socket socket = serverSocket.accept();
                headerOutput.parseRequest(socket);
                headerOutput.outputStream(socket);
                socket.close();
            }

            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}