package com.company;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicAuthenticationTest {
    @Test
    public void getEncodedCredentialsStringValue() throws Exception {
        BasicAuthenticationHandler basicAuthenticationHandler = new BasicAuthenticationHandler();
        String username = "admin";
        String password = "hunter2";

        String encodedPasswordValue = "YWRtaW46aHVudGVyMg==";

        assertEquals(encodedPasswordValue, basicAuthenticationHandler.authenticationCredentials());
    }

    @Test
    public void authenticateTheRequest() throws Exception {
        BasicAuthenticationHandler basicAuthenticationHandler = new BasicAuthenticationHandler();

        String data = "GET /logs HTTP/1.1";
        String encodedPasswordValue = "YWRtaW46aHVudGVyMg==";


//        assertEquals(,basicAuthentication.authenticate(data));
    }
}
