package com.company.Routes;

import com.company.request.Request;

public interface IResponse {
    public byte[] getBody(Request request) throws Exception;
}