package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SocketServerTest {
    private SocketServer server;
    private int port;

    public class FakeSocket extends Socket {
        private final String response;

        public FakeSocket(String s) {
            response = s;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(response.getBytes());
        }
    }

    @Before
    public void setUp() throws Exception {
        port = 5000;
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
    public void ServerIsConnected() throws Exception {
        server.start();
        Socket socket = new Socket("localhost", 5000);
        server.stop();

        assertEquals(true, socket.isConnected());
    }

}