package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
	SocketService socketService = new SocketService();
        socketService.serve();
    }
}