package com.company.Server;

import com.company.FileRouter;
import com.company.Handler.PatchRequestHandler;
import com.company.Handler.PostRequestHandler;
import com.company.Handler.StreamHandler;
import com.company.Response.FileRetriever;
import com.company.Response.HTTPStatusCodes;
import com.company.Response.RequestBuilder;
import com.company.request.RequestParser;
import com.company.Response.Headers.*;

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
    private BodyContents bodyContents;
    private BodyLength bodyLength;
    private String method;
    private String data;
    private String byteCount;
    private int port;

    public ResponseHandler(int port) {
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages(new HTTPStatusCodes());
        streamHandler = new StreamHandler(new RequestBuilder());
        serverLocation = new ServerLocation();
        allowMethods = new AllowMethods();
        FileRetriever fileRetriever = new FileRetriever();
        bodyContents = new BodyContents(new FileRouter(new PostRequestHandler(fileRetriever), new PatchRequestHandler(fileRetriever)));
        bodyLength = new BodyLength();
        this.port = port;
    }

    public void parseRequest(Socket socket) throws IOException {
        String stream = streamHandler.convertRawRequestToString(socket);
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