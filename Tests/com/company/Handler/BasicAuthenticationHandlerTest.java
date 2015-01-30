package com.company.Handler;

import com.company.Handler.BasicAuthenticationHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicAuthenticationHandlerTest {
    @Test
    public void encodeAuthenticationCredentials() throws Exception {
        BasicAuthenticationHandler basicAuthenticationHandler = new BasicAuthenticationHandler();

        assertEquals("YWRtaW46aHVudGVyMg==", basicAuthenticationHandler.encodeAuthenticationCredentials());
    }

    @Test
    public void parseAuthenticationDataRequest() throws Exception {
        BasicAuthenticationHandler basicAuthenticationHandler = new BasicAuthenticationHandler();

        String data = "GET /logs HTTP/1.1";
        String authenticationString = "Authentication required";

        assertEquals(authenticationString, basicAuthenticationHandler.parseAuthenticationData(data));
    }
}
