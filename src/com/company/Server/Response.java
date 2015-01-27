package com.company.Server;

import com.company.Response.HTTPStatusCodes;
import com.company.Response.Headers.*;
import com.company.request.Request;

import java.io.*;
import java.net.*;

public class Response {
    private ContentType contentType;
    private DateAndTime dateAndTime;
    private StatusMessages statusMessages;
    private ServerLocation serverLocation;
    private AllowMethods allowMethods;
    private BodyContents bodyContents;
    private BodyLength bodyLength;
    private int port;
    private Request request;

    public Response(int port, Request request) {
        this.request = request;
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages(new HTTPStatusCodes());
        serverLocation = new ServerLocation();
        allowMethods = new AllowMethods();
        bodyContents = new BodyContents();
        bodyLength = new BodyLength();
        this.port = port;
    }
    
    public void sendResponse(Socket socket) throws Exception {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        String method = request.getMethod();
        String filePath = request.getFilePath();
        String data = request.getData();
        String byteCount = request.getByteCount();

        byte[] body = this.bodyContents.getBody(method, filePath, data, byteCount);
        byte[] statusMessage = statusMessages.getStatusMessage(method, filePath, data);
        byte[] time = dateAndTime.getServerTime();
        byte[] location = serverLocation.getLocationResponse(port);
        byte[] type = contentType.getContentTypeHeader(filePath);
        byte[] allow = allowMethods.getAllowResponse();
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