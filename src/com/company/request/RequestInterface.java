package com.company.request;

import java.io.IOException;
import java.net.Socket;

public interface RequestInterface {
//    This interface will have the method(s) that are needed for getting the actual request
    public String getRequest(Socket socket) throws IOException;
}