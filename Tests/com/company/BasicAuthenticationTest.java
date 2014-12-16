package com.company;

import org.junit.Test;
import sun.misc.BASE64Encoder;

import static org.junit.Assert.assertEquals;

public class BasicAuthenticationTest {
    @Test
    public void getEncodedCredentialsStringValue() throws Exception {
        BasicAuthentication basicAuthentication = new BasicAuthentication();
        String username = "admin";
        String password = "hunter2";

        String encodedPasswordValue = "YWRtaW46aHVudGVyMg==";

        assertEquals(encodedPasswordValue, basicAuthentication.authenticationCredentials());
    }

    @Test
    public void authenticateTheRequest() throws Exception {
        BasicAuthentication basicAuthentication = new BasicAuthentication();

        String data = "GET /logs HTTP/1.1";
        String encodedPasswordValue = "YWRtaW46aHVudGVyMg==";


//        assertEquals(,basicAuthentication.authenticate(data));
    }
}
