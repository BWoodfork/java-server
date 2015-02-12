package main.com;

import main.com.mycompany.Server.SocketServer;

public class Main {

    public static void main(String[] args) throws Exception {
	    SocketServer socketServer = new SocketServer(Integer.parseInt(args[0]));
        socketServer.start();
    }
}