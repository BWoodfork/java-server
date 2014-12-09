package com.company.HeaderData;

import com.company.*;

import java.io.*;
import java.net.Socket;

public class HeaderOutput {
    private RequestParser requestParser;
    private ContentType contentType;
    private DateAndTime dateAndTime;
    private StatusMessages statusMessages;
//    private String builtRequest;

    public HeaderOutput() {
        contentType = new ContentType();
        dateAndTime = new DateAndTime();
        statusMessages = new StatusMessages();
    }

    public BufferedReader reader(Socket socket) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        return new BufferedReader(inputStreamReader);
    }

    public StringBuilder buildRequest(Socket socket) throws IOException {
        BufferedReader bufferedReader = reader(socket);

        StringBuilder theRequestString = new StringBuilder();
        String line;

        do {
            line = bufferedReader.readLine();
            theRequestString.append(line);
            if (line.equals("")) break;
        } while (true);

        return theRequestString;
    }

    public byte[] requestBytes(Socket socket) throws IOException {
        String builtRequest = buildRequest(socket).toString();
//        System.out.println(builtRequest);
        return builtRequest.getBytes();
    }

//    public void requestToFile() throws IOException {
//        FileOutputStream fileOutputStream = new FileOutputStream("/Users/8thlight/projects/cob_spec/logger.txt");
//        fileOutputStream.write(builtRequest.getBytes());
//    }

    public String requestToString(Socket socket) throws IOException {
        return new String(requestBytes(socket));
    }

    public RequestParser parseRequest(Socket socket) throws IOException {
        return requestParser = new RequestParser(requestToString(socket));
    }

    public void requestToFile() throws IOException {
        System.out.println(requestParser.getMethod() + " " + requestParser.getFilePath() + " " + requestParser.parsedProtocol());
        String request = requestParser.getMethod() + " " + requestParser.getFilePath() + " " + requestParser.parsedProtocol();

        byte[] parser = request.getBytes();

//        FileOutputStream fileOutputStream = new FileOutputStream("/Users/8thlight/projects/cob_spec/logger.txt");
//        fileOutputStream.write(parser);

        FileWriter fileWriter = new FileWriter("/Users/8thlight/projects/cob_spec/logs.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("GET /log HTTP/1.1");
        bufferedWriter.flush();
    }

//    public void requestToFile(Socket socket) throws IOException {
//        FileOutputStream fos = new FileOutputStream("/Users/8thlight/projects/cob_spec/logger.txt");
//        fos.write(requestBytes(socket));
//        fos.close();
//    }

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
        String content = requestParser.getFilePath();

        String type = "Content-Type: " + contentType.getContentType(content);
        return type.getBytes();
    }

    public byte[] pageBodyBytes() throws IOException {
        FileRouter fileRouter = new FileRouter();

        return fileRouter.routeFiles(requestParser.getFilePath());
    }
}