package com.company;

import java.io.*;
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
        out.print("Content-Length: " + getContentLength(pageBody(socket)) + "\r\n\r\n");
        out.print(pageBody(socket));
        out.flush();
    }

    public int getContentLength(String content) {
        return content.getBytes().length;
    }

    public String pageBody(Socket socket) throws IOException {
      return readFile1();
    }

//    public String parseGetRequest(Socket socket) throws IOException {
//        if (splitGetRequest(socket)[1] == "/file1") {
//            readFile1();
//        }
//            return "Something is wrong here.";
//    }

    public String[] splitGetRequest(Socket socket) throws IOException {
        return getRequest(socket).split(" ");
    }

    public String readFile1() throws IOException {
        FileReader fileReader = new FileReader("/Users/ByronWoodfork/Projects/cob-spec/cob_spec/public/file1");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.readLine();
    }

    public String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }
}