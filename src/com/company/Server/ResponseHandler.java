package com.company.Server;

import com.company.Handler.StreamHandler;
import com.company.Parser.RequestParser;
import com.company.Response.Headers.*;

import java.io.*;
import java.net.*;

public class ResponseHandler {
//    private Response contentType;
    private ContentType contentType;
    private DateAndTime dateAndTime;
    private StatusMessages statusMessages;
    private StreamHandler streamHandler;
    private String filePath;
    private ServerLocation serverLocation;
    private AllowMethods allowMethods;
    private BodyContents bodyContents;
    private BodyLength bodyLength;
    private String method;
    private String data;
    private String byteCount;
    private int port;

    public ResponseHandler(int port) {
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages();
        streamHandler = new StreamHandler();
        serverLocation = new ServerLocation();
        allowMethods = new AllowMethods();
        bodyContents = new BodyContents();
        bodyLength = new BodyLength();
        this.port = port;
    }

    public void parseRequest(Socket socket) throws IOException {
        String stream = streamHandler.getInputStreamStringValue(socket);
        RequestParser requestParser = new RequestParser(stream);

        method = requestParser.getMethod();
        filePath = requestParser.getFilePath();
        data = requestParser.getData();
        byteCount = requestParser.getByteCount();
    }

    public void sendResponse(Socket socket) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        byte[] body = this.bodyContents.getBody(method, filePath, data, byteCount);
        byte[] statusMessage = statusMessages.getStatusMessage(method, filePath, data);
        byte[] time = dateAndTime.getServerTime();
        byte[] location = serverLocation.getLocationResponse(port);
        byte[] type = contentType.getContentTypeHeader(filePath);
//        type = contenType.getResponse(filePath);
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