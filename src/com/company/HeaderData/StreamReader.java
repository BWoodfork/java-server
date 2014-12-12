package com.company.HeaderData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class StreamReader {

    public BufferedReader readStream(InputStream stream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        return new BufferedReader(inputStreamReader);
    }

    public BufferedReader getStream(Socket socket) throws IOException {
        return readStream(socket.getInputStream());
    }
}