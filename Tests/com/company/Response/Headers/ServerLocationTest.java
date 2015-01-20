package com.company.Response.Headers;

import com.company.Reponse.Headers.ServerLocation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerLocationTest {
    @Test
    public void returnsTheServerLocationInfo() throws Exception {
        ServerLocation serverLocation = new ServerLocation();

        String locationString = "Location: http://localhost:5000/" + "\r\n";
        String serverLocationString = new String(serverLocation.getLocationResponse());
        
        assertEquals(locationString, serverLocationString);
    }
}