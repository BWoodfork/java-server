package com.company.HeaderData;

import com.company.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HeaderOutput {
    private RequestParser requestParser;
    private ContentType contentType;
    private DateAndTime dateAndTime;
    private StatusMessages statusMessages;

    public HeaderOutput() {
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages();
    }

    public void parseRequest(Socket socket) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        do {
            line = bufferedReader.readLine();
            stringBuilder.append(line);
            if (line.equals("")) break;

        } while (true);

        requestParser = new RequestParser(stringBuilder.toString());
    }

    public void outputStream(Socket socket) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        byte[] body = pageBodyBytes();
        String length = "Content-Length: " + Integer.toString(body.length) + "\r\n\r\n";

        out.write(statusMessages.getStatusMessage(requestParser));
        out.write(dateAndTime.getServerTime());
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
        String type = "Content-Type: " + contentType.getContentType(requestParser);
        return type.getBytes();
    }

    public byte[] pageBodyBytes() throws IOException {
        FileRouter fileRouter = new FileRouter();

        return fileRouter.routeFiles(requestParser);
    }
}