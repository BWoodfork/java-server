package com.company.Response.Headers;

import com.company.Reponse.Headers.AllowMethods;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AllowMethodsTest {
    @Test
    public void returnsAllowHeaders() throws Exception {
        AllowMethods allowMethods = new AllowMethods();
        byte[] allow = "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n".getBytes();
        String allowString = new String(allow);
        String getAllowResponseString = new String(allowMethods.getAllowResponse());
        
        assertEquals(allowString, getAllowResponseString);
    }
}