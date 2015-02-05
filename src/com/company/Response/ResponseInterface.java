package com.company.Response;

import com.company.request.RequestInterface;

public interface ResponseInterface {
// getResponse method will go here and it will be passed the RequestInterface as an arg.
    public String setStatus(String statusCode);
}