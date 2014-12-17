package com.company.HeaderData;

import com.company.*;
import java.io.*;
import java.net.*;

public class HeaderOutput {
    private ContentType contentType;
    private DateAndTime dateAndTime;
    private StatusMessages statusMessages;
    private RequestParser requestParser;
    private StreamHandler streamHandler;

    public HeaderOutput() {
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages();
        streamHandler = new StreamHandler();
    }

    public RequestParser parseRequest(Socket socket) throws IOException {
        String stream = streamHandler.getInputStreamStringValue(socket);
        return requestParser = new RequestParser(stream);
    }

    public void outputStream(Socket socket) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        byte[] body = pageBodyBytes();
        String length = "Content-Length: " + Integer.toString(body.length) + "\r\n\r\n";

        out.write(statusMessages.getStatusMessage(requestParser.getFullRequest()));
        out.write(dateAndTime.getServerTime());
        out.write(locationHeaderBytes());
        out.write(contentTypeBytes());
        out.write(allowHeaderBytes());
        out.write(length.getBytes());
        out.write(body);
        out.flush();
    }

    public byte[] allowHeaderBytes() {
        String allow = "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n";
        return allow.getBytes();
    }

    public byte[] contentTypeBytes() {
        String content = requestParser.getFilePath();

        String type = "Content-Type: " + contentType.getContentType(content);
        return type.getBytes();
    }

    public byte[] locationHeaderBytes() {
        String location = "Location: http://localhost:5000/";
        String locationHeader = location + "\r\n";

        return locationHeader.getBytes();
    }

    public byte[] pageBodyBytes() throws IOException {
        FileRouter fileRouter = new FileRouter();
        String path = requestParser.getFullRequest();

        return fileRouter.routeFiles(path);
    }
}