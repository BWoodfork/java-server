package me.byronwoodfork;

import org.junit.Test;

import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class SocketWrapperTest extends Socket {
    
    @Test
    public void returnsTrueIfSocketWrapperIsOfTypeSocket() throws Exception {
        Socket socket = new Socket();
        SocketWrapper socketWrapper = new SocketWrapper(socket);
        
        assertEquals(socket.getClass(), socketWrapper.getSocket().getClass());
    }
}