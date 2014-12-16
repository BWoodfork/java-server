package com.company.HeaderData;

import com.company.*;
import java.io.*;
import java.net.*;

public class HeaderOutput {
    private ContentType contentType;
    private DateAndTime dateAndTime;
    private StatusMessages statusMessages;
    private RequestParser requestParser;
    private RequestBuilder requestBuilder;
    private StreamReader streamReader;

    public HeaderOutput() {
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages();
        requestBuilder = new RequestBuilder();
        streamReader = new StreamReader();
    }

    public String getStream(Socket socket) throws IOException {
        BufferedReader stream = streamReader.readStream(socket.getInputStream());
        return requestBuilder.getRequestString(stream);
    }

    public RequestParser parseRequest(Socket socket) throws IOException {
        return requestParser = new RequestParser(getStream(socket));
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

//    public byte[] contentRange() {
//        System.out.println(requestParser.getFilePath());
//
//        String range = "Content-Range: bytes 0-10\r\n";
//
//        return range.getBytes();
//    }

//    public byte[] postMethodBytes() {
//        String post = "POST ";
//        String request = post + requestParser.getFilePath();
//
//        return request.getBytes();
//    }

    public byte[] acceptRangeHeader() {
        String accept = "Accept-Ranges: bytes";

        return accept.getBytes();
    }

    public byte[] pageBodyBytes() throws IOException {
        FileRouter fileRouter = new FileRouter();
        String path = requestParser.getFullRequest();

        return fileRouter.routeFiles(path);
    }
}