package main.java.company.Response.ResponseBuilders;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusBuilderTest {
    @Test
    public void returnsTheHTTPStatusAfterItHasBeenSet() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        statusBuilder.setHTTPStatus(200);
        assertEquals("HTTP/1.1 200 OK\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void returns404NotFoundStatus() throws Exception {
        StatusBuilder statusBuilder = new StatusBuilder();
        statusBuilder.setHTTPStatus(404);
        assertEquals("HTTP/1.1 404 NOT FOUND\r\n", new String(statusBuilder.getHTTPStatus()));
    }
}
