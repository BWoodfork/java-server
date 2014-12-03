package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class SocketService {
    private RequestParser requestParser;
    private HTTPResponse response;

    public SocketService() {
        response = new HTTPResponse();
    }

    public void serve() throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);

        try {
            while(true) {
                Socket socket = serverSocket.accept();
                parseRequest(socket);
                outputStream(socket);
                socket.close();
            }

            } catch (IOException e) {
                e.printStackTrace();
            }

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
        byte[] body = body();
        String length = "Content-Length: " + Integer.toString(body.length) + "\r\n\r\n";

        out.write(statusMessageBytes());
        out.write(getServerTimeBytes());
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
        String type = "Content-Type: text/html\r\n";
        return type.getBytes();
    }

    public byte[] statusMessageBytes() {
        String filepath = requestParser.getFilePath();

        String status = "HTTP/1.1 " + response.getResponseStatus(filepath) + "\r\n";
        return status.getBytes();
    }

    public byte[] body() throws IOException {
        FileRouter fileRouter = new FileRouter();

        return fileRouter.routeFiles(requestParser);
    }

    public String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }

    public byte[] getServerTimeBytes() {
        String serverTime = getServerTime() + "\r\n";
        return serverTime.getBytes();
    }
}