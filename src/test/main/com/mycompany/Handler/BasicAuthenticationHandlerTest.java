package main.com.mycompany.Handler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicAuthenticationHandlerTest {
    private BasicAuthenticationHandler basicAuthenticationHandler;

    @Before
    public void setUp() throws Exception {
        basicAuthenticationHandler = new BasicAuthenticationHandler();
    }

    @Test
    public void encodeAuthenticationCredentials() throws Exception {
        assertEquals("YWRtaW46aHVudGVyMg==", basicAuthenticationHandler.encodeAuthenticationCredentials());
    }
}