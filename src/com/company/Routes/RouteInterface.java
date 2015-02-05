package com.company.Routes;

import com.company.request.Request;

public interface RouteInterface {
    public byte[] getBody(Request request) throws Exception;
//    public void execute(Request request);
}