package com.company.Handler;

import com.company.Response.HTTPStatusCodes;
import com.company.Server.Response;
import com.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicAuthenticationHandlerTest {
    private BasicAuthenticationHandler basicAuthenticationHandler;

//    @Before
//    public void setUp() throws Exception {
//        HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
//        basicAuthenticationHandler = new BasicAuthenticationHandler(httpStatusCodes);
//    }
//
//    @Test
//    public void encodeAuthenticationCredentials() throws Exception {
//        assertEquals("YWRtaW46aHVudGVyMg==", basicAuthenticationHandler.encodeAuthenticationCredentials());
//    }
//
//    @Test
//    public void parseAuthenticationDataRequest() throws Exception {
//        String data = "GET /logs HTTP/1.1";
//        String authenticationString = "Authentication required";
//
//        assertEquals(authenticationString, basicAuthenticationHandler.parseAuthenticationData(data));
//    }
//
//    @Test
//    public void returnsTrueIfRequestIsMadeWithoutCredentials() throws Exception {
//        String data = "GET /logs HTTP/1.1";
//
//        assertEquals(false, basicAuthenticationHandler.hasCredentials(data));
//    }
//
//    @Test
//    public void returnsFalseIfRequestIsMadeWithCredentials() throws Exception {
//        String data = basicAuthenticationHandler.encodeAuthenticationCredentials() + "Connection:";
//
//        assertEquals(true, basicAuthenticationHandler.hasCredentials(data));
//    }
}
