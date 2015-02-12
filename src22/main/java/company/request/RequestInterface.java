package main.java.company.request;

import java.io.IOException;

public interface RequestInterface {
//    This interface will have the method(s) that are needed for getting the actual request
    public String getRequest() throws IOException;
    public String setStatusCode(String statusCode);
}