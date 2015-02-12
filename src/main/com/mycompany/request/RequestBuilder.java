package main.com.mycompany.request;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestBuilder {
    public StringBuilder buildRequest(BufferedReader bufferedReader) throws IOException {
        StringBuilder theRequestString = new StringBuilder();
        String line;

        do {
            line = bufferedReader.readLine();
            theRequestString.append(line);
            if (line.equals("")) break;
        } while (bufferedReader.ready());

        return theRequestString;
    }

    public byte[] getBuiltRequest(BufferedReader bufferedReader) throws IOException {
        String request = buildRequest(bufferedReader).toString();
        return request.getBytes();
    }

    public String getRequestString(BufferedReader bufferedReader) throws IOException {
        return new String(getBuiltRequest(bufferedReader));
    }
}