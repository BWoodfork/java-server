package main.java.company.Response.ResponseBuilders;

import main.java.company.Response.Headers.Options;
import main.java.company.request.Request;

public abstract class ResponseBuilder {
    abstract public byte[] buildResponse(Request request, Options options) throws Exception;
}