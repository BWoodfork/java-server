package com.company;

import com.company.Server.SocketServer;

public class Main {

    public static void main(String[] args) throws Exception {
	    SocketServer socketServer = new SocketServer(5000);
        socketServer.start();
    }
}