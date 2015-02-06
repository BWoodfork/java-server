package com.company.Routes;

import com.company.request.Request;

public interface ResponseInterface {
    public byte[] getBody(Request request) throws Exception;
}