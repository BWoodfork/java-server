package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class SocketService {
    private RequestParser parser;
    private HTTPResponse response;
    private FileRouter router;

    public SocketService() {
        response = new HTTPResponse();
    }

    public void serve() throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);

        try {
            while(true) {
                Socket socket = serverSocket.accept();
                String getRequest = inputStream(socket).readLine();
//                System.out.println(getRequest);
                parser = new RequestParser(getRequest);
                outPutStream(socket);
                socket.close();
            }

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public BufferedReader inputStream(Socket socket) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        return new BufferedReader(inputStreamReader);
    }

    public void outPutStream(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        String theFilePath = parser.getFilePath();

        out.print("HTTP/1.1 " + response.getResponseStatus(theFilePath) + "\r\n");
        out.print("Date: " + getServerTime() + "\r\n");
        out.print("Content-Type: text/html\r\n");
        out.print("Allow: GET,HEAD,POST,OPTIONS,PUT\r\n");
        out.print("Content-Length: " + getContentLength(pageBody()) + "\r\n\r\n");
        out.print(pageBody());
        out.flush();
    }

    public int getContentLength(String content) {
        return content.getBytes().length;
    }

    public String pageBody() throws IOException {
//        String path = parser.getFilePath();

        return parser.routeFile1();
    }

    public String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }
}