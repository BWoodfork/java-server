package me.byronwoodfork;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketWrapper extends Socket {
    private Socket socket;
    
    public SocketWrapper(Socket socket) {
        this.socket = socket;
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
    
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }
    
    public boolean isConnected() {
        return socket.isClosed();
    }
}