package com.company.Response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusCodesTest {
    @Test
    public void returnsTwoHundredOk() throws Exception {
        assertEquals("200 OK", StatusCodes.twoHundredOk());
    }
    
    @Test
    public void returnsFourOhFiveOk() throws Exception {
        assertEquals("405 OK", StatusCodes.fourOhFiveOk());
    }
    
    @Test
    public void returnsTwoOhFourNoContent() throws Exception {
        assertEquals("204 No Content", StatusCodes.twoOhFourNoContent());
    }
    
    @Test
    public void returnsFourOhOneUnauthorized() throws Exception {
        assertEquals("401 Unauthorized", StatusCodes.fourOhOneUnauthorized());
    }
    
    @Test
    public void returnsFourOhFiveNoMethodAllowed() throws Exception {
        assertEquals("405 Method Not Allowed", StatusCodes.fourOhFiveNotAllowed());
    }
    
    @Test
    public void returnsThreeOhOneMovedPermanently() throws Exception {
        assertEquals("301 Moved Permanently", StatusCodes.threeOhOneMoved());
    }
    
    @Test
    public void returnsTwoOhSixPartialContent() throws Exception {
        assertEquals("206 Partial Content", StatusCodes.twoOhSixPartial());
    }
    
    @Test
    public void returnsFourOhFourNotFound() throws Exception {
        assertEquals("404 NOT FOUND", StatusCodes.fourOhFourNotFound());
    }
}
