package com.company.Response.Headers;

<<<<<<< HEAD
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
=======
/**
 * Created by 8thlight on 1/20/15.
 */
public class ServerLocationTest {
}
>>>>>>> 30a5e4862ad773ea52696a71d8bfd97ea0a28cf1
