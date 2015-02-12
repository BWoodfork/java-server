package main.java.company.Server;

import main.java.company.Response.ResponseBuilders.StatusBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SocketServerTest {
    private SocketServer server;
    private int port;
    private StatusBuilder statusBuilder;

    @Before
    public void setUp() throws Exception {
        port = 5005;
        server = new SocketServer(port);
        statusBuilder = new StatusBuilder();
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
        assertEquals(1, server.getConnections());
    }

//    @Test
//    public void ServerAcceptsTwoConnections() throws  Exception {
//        server.start();
//        new Socket("localhost", port);
//        new Socket("localhost", port);
//        server.stop();
//        assertEquals(2, server.getConnections());
//    }
    
//    @Test
//    public void doesSomething() throws Exception {
//        PrintWriter printWriter = new PrintWriter(server.serveTheSocket().getOutputStream(), true);
//        printWriter.println("GET / HTTP/1.1\r");
//        printWriter.flush();
//
//        RequestBuilder requestBuilder = new RequestBuilder();
//        StreamHandler streamHandler = new StreamHandler(requestBuilder);
//    }

    @Test
    public void ServerIsConnected() throws Exception {
        server.start();
        Socket socket = new Socket("localhost", port);
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
        assertEquals(50, server.getConnections());
    }
    
//    public class MockSocket extends SocketServer {
//        public MockSocket(int port) throws IOException {
//            super(port);
//        }
//
//        @Override
//        public void start() {
//
//        }
//    }

    private void makeFakeGetRequest(Socket socket) throws Exception {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("GET / HTTP/1.1\r");
        printWriter.println("\r");
        printWriter.flush();
    }
}