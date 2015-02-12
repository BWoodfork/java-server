package main.com.mycompany.Response.ResponseBuilders;

import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.request.Request;

public abstract class ResponseBuilder {
    abstract public byte[] buildResponse(Request request, Options options) throws Exception;
}