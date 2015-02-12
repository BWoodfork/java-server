package main.com.mycompany.Response.Headers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsTest {
    private Options options;
    
    @Before
    public void setUp() throws Exception {
        options = new Options();
    }
    
    @Test
    public void removesWhiteSpaceFromOptionsString() throws Exception {
        String methodString = "GET, POST, PUT";

        assertEquals("GET,POST,PUT", options.formatOptionsString(methodString));
    }
    
    @Test
    public void capitalizesOptionString() throws Exception {
        String methodString = "get, post, put";
        
        assertEquals("GET,POST,PUT", options.formatOptionsString(methodString));
    }

    @Test
    public void parseOptionsString() throws Exception {
        options.setOptions("POST, PUT, GET, DELETE");
        String[] expectedOptionsList = {"POST", "PUT", "GET", "DELETE"};
        
        assertEquals(expectedOptionsList ,options.splitOptions(options.getFormattedOptionsString()));
    }
    
    @Test
    public void parseOptionStringWithNoSpaces() throws Exception {
        options.setOptions("POST,PUT,GET,DELETE");
        String[] expectedOptionsList = {"POST", "PUT", "GET", "DELETE"};

        assertEquals(expectedOptionsList ,options.splitOptions(options.getFormattedOptionsString()));
    }
    
    @Test
    public void returnsMethodOptionStringIfItMatchesMethodInMethodOptionsMap() throws Exception {
        String optionString = "GET";
        
        assertEquals("GET" ,options.getMethodOption(optionString));
    }
    
    @Test
    public void returnsPUTMethodIfItMatchesAnElementInMethodOptionsMap() throws Exception {
        String optionString = "PUT";
        
        assertEquals("PUT", options.getMethodOption(optionString));
    }
    
    @Test
    public void returnsMethodNotValidIfOptionDoesNotExist() throws Exception {
        String optionString = "NOPE";

        assertEquals("Method Not Valid" ,options.getMethodOption(optionString));
    }

    @Test
    public void returnsTrueIfOptionsStringsAreValid() throws Exception {
        String[] optionsList = {"POST", "PUT", "GET"};
        
        assertEquals(true, options.isAValidOption(optionsList));
    }
    
    @Test
    public void returnsFalseIfOptionsStringsAreInvalid() throws Exception {
        String[] optionsList = {"POST", "NOPE", "NAH"};
        
        assertEquals(false, options.isAValidOption(optionsList));
    }

    @Test
    public void getOptionsReturnsAllowHeaderFormatted() throws Exception {
        options.setOptions("GET, POST, PUT, DELETE");

        assertEquals("Allow: GET,POST,PUT,DELETE\r\n", options.getOptions());
    }
}
