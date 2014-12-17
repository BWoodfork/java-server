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

    public HeaderOutput() {
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages();
        streamHandler = new StreamHandler();
        serverLocation = new ServerLocation();
        allowMethods = new AllowMethods();
        serverBody = new ServerBody();
    }

    public void parseRequest(Socket socket) throws IOException {
        String stream = streamHandler.getInputStreamStringValue(socket);
        RequestParser requestParser = new RequestParser(stream);

        fullRequest = requestParser.getFullRequest();
        filePath = requestParser.getFilePath();
    }

    public void outputStream(Socket socket) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        byte[] body = serverBody.getBody(fullRequest);
        String length = "Content-Length: " + Integer.toString(body.length) + "\r\n\r\n";

        out.write(statusMessages.getStatusMessage(fullRequest));
        out.write(dateAndTime.getServerTime());
        out.write(serverLocation.getLocationResponse());
        out.write(contentType.contentTypeResponse(filePath));
        out.write(allowMethods.getAllowResponse());
        out.write(length.getBytes());
        out.write(body);
        out.flush();
    }
}