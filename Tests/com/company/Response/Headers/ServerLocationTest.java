package com.company.Response.Headers;

import com.company.Reponse.Headers.ServerLocation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerLocationTest {
    @Test
    public void returnsTheServerLocation() throws Exception {
        ServerLocation serverLocation = new ServerLocation();
        int port = 5000;
        String serverlocationString = "Location: http://localhost:5000/" + "\r\n";
        String getServerLocationString = new String(serverLocation.getLocationResponse(port));
        
        assertEquals(serverlocationString, getServerLocationString);
    }
}