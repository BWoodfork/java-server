package com.company.Response.Headers;

import com.company.Response.HTTPStatusCodes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusMessagesTest {
    private HTTPStatusCodes httpStatusCodes;
    
    public StatusMessagesTest(HTTPStatusCodes httpStatusCodes) {
        this.httpStatusCodes = httpStatusCodes;
    }
    
    @Test
    public void returnsTheStatusMessageForA200OkRequest() throws Exception {
        StatusMessages statusMessages = new StatusMessages(httpStatusCodes);
        
        String method = "GET";
        String path = "/file2";
        String data = "";
        
        String statusMessageString = new String(statusMessages.getStatusMessage(method, path, data));
        String twoHundredOk = "HTTP/1.1 200 OK" + "\r\n";
        
        assertEquals(twoHundredOk, statusMessageString);
    }
    
    @Test
    public void returnsTheStatusMessageForA405Request() throws Exception {
        StatusMessages statusMessages = new StatusMessages(httpStatusCodes);

        String method = "GET";
        String path = "/file1";
        String data = "";

        String statusMessageString = new String(statusMessages.getStatusMessage(method, path, data));
        String fourOhFiveStatus = "HTTP/1.1 405 OK" + "\r\n";

        assertEquals(fourOhFiveStatus, statusMessageString);
    }
}