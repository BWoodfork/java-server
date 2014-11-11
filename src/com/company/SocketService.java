package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class SocketService {

    public void serve() throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        try {
            while(true) {
                Socket socket = serverSocket.accept();
                outPutStream(socket);
                socket.close();
            }

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public String getRequest(Socket socket) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.readLine();
    }

    public void outPutStream(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        out.print("HTTP/1.1 200 OK\r\n");
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

    public String pageBody() {
        return "<html><body> Hello World </body></html>";
    }

    public String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }
}