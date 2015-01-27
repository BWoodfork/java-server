package com.company.Server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SocketServerTest {
    private SocketServer server;
    private int port;
    
    @Before
    public void setUp() throws Exception {
        port = 5001;
        server = new SocketServer(port);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void ServerStartsAndStops() throws Exception {
        server.start();
        assertTrue(server.isRunning());
        server.stop();
        assertFalse(server.isRunning());
    }

    @Test
    public void ServerAcceptsOneConnection() throws Exception {
        server.start();
        new Socket("localhost", port);
        server.stop();
        assertEquals(1, server.connections);
    }

    @Test
    public void ServerAcceptsTwoConnections() throws  Exception {
        server.start();
        new Socket("localhost", port);
        new Socket("localhost", port);
        server.stop();
        assertEquals(2, server.connections);
    }

    @Test
    public void ServerIsConnected() throws Exception {
        server.start();
        Socket socket = new Socket("localhost", 5000);
        server.stop();

        assertEquals(true, socket.isConnected());
    }

    @Test
    public void Accepts50Connections() throws Exception {
        server.start();

        for (int i = 1; i <= 50; i++) {
            new Socket("localhost", port);
        }

        server.stop();
        assertEquals(50, server.connections);
    }

}