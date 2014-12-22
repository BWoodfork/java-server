package com.company.HeaderData;

import com.company.*;
import java.io.*;
import java.net.*;

public class ResponseHandler {
    private ContentType contentType;
    private DateAndTime dateAndTime;
    private StatusMessages statusMessages;
    private StreamHandler streamHandler;
    private String filePath;
    private ServerLocation serverLocation;
    private AllowMethods allowMethods;
    private Body body;
    private BodyLength bodyLength;
    private String method;
    private String data;
    private String byteCount;

    public ResponseHandler() {
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages();
        streamHandler = new StreamHandler();
        serverLocation = new ServerLocation();
        allowMethods = new AllowMethods();
        body = new Body();
        bodyLength = new BodyLength();
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

        byte[] body = this.body.getBody(method, filePath, data, byteCount);
        byte[] statusMessage = statusMessages.getStatusMessage(method, filePath, data);
        byte[] time = dateAndTime.getServerTime();
        byte[] location = serverLocation.getLocationResponse();
        byte[] type = contentType.contentTypeResponse(filePath);
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