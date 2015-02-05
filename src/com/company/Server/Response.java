package com.company.Server;

import com.company.Response.Body;
import com.company.Response.Headers.*;
import com.company.Utilities.FileMatcher;
import com.company.Utilities.StatusBuilder;
import com.company.request.Request;

import java.io.*;
import java.net.*;

public class Response {
    private ContentType contentType;
    private DateAndTime dateAndTime;
    private ServerLocation serverLocation;
    private AllowMethods allowMethods;
    private Body body;
    private BodyLength bodyLength;
    private int port;
    private Request request;
    private StatusBuilder statusBuilder;

    public Response(int port, Request request) {
        this.request = request;
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        serverLocation = new ServerLocation();
        statusBuilder = new StatusBuilder();
        FileMatcher fileMatcher = new FileMatcher();
        allowMethods = new AllowMethods(fileMatcher);
        body = new Body();
        bodyLength = new BodyLength();
        this.port = port;
    }

    public void sendResponse(Socket socket) throws Exception {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        String method = request.getMethod();
        String filePath = request.getFilePath();

        byte[] body = this.body.getBody(statusBuilder, request);
        byte[] statusMessage = statusBuilder.getHTTPStatus();
        byte[] time = dateAndTime.getServerTime();
        byte[] location = serverLocation.getLocationResponse(port);
        byte[] type = contentType.getContentTypeHeader(filePath);
        byte[] allow = allowMethods.getAllowResponse(method, filePath);
        byte[] length = bodyLength.getBodyLength(body);

        out.write(statusMessage);
        out.write(time);
        out.write(location);
        out.write(type);
        out.write(allow);
        out.write(length);
        out.write(body);
        out.flush();
    }
}