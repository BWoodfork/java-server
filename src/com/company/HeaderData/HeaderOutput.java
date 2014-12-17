package com.company.HeaderData;

import com.company.*;
import java.io.*;
import java.net.*;

public class HeaderOutput {
    private ContentType contentType;
    private DateAndTime dateAndTime;
    private StatusMessages statusMessages;
    private StreamHandler streamHandler;
    private String fullRequest;
    private String filePath;
    private ServerLocation serverLocation;
    private AllowMethods allowMethods;
    private ServerBody serverBody;
    private ServerBodyLength serverBodyLength;
    private String method;
    private String data;

    public HeaderOutput() {
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages();
        streamHandler = new StreamHandler();
        serverLocation = new ServerLocation();
        allowMethods = new AllowMethods();
        serverBody = new ServerBody();
        serverBodyLength = new ServerBodyLength();
    }

    public void parseRequest(Socket socket) throws IOException {
        String stream = streamHandler.getInputStreamStringValue(socket);
        RequestParser requestParser = new RequestParser(stream);

        fullRequest = requestParser.getFullRequest();
        method = requestParser.getMethod();
        filePath = requestParser.getFilePath();
        data = requestParser.getData();
    }

    public void sendResponse(Socket socket) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        byte[] body = serverBody.getBody(fullRequest);
        byte[] statusMessage = statusMessages.getStatusMessage(method, filePath, data);
        byte[] time = dateAndTime.getServerTime();
        byte[] location = serverLocation.getLocationResponse();
        byte[] type = contentType.contentTypeResponse(filePath);
        byte[] allow = allowMethods.getAllowResponse();
        byte[] length = serverBodyLength.getBodyLength(body);

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