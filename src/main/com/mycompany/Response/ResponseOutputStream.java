package main.com.mycompany.Response;

import main.com.mycompany.Response.Headers.Options;
import main.com.mycompany.Response.ResponseBuilders.StatusBuilder;
import main.com.mycompany.request.Request;

import java.io.*;
import java.net.*;

public class ResponseOutputStream {
    private Response response;
    private int port;
    private Request request;
    private StatusBuilder statusBuilder;
    private Options options;

    public ResponseOutputStream(int port, Request request) {
        this.request = request;
        statusBuilder = new StatusBuilder();
        response = new Response();
        this.port = port;
        options = new Options();
    }

    public void sendResponse(Socket socket) throws Exception {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        byte[] time = response.getDate();
        byte[] location = response.getServerLocation(port);
        byte[] type = response.getContentType(request);
        byte[] length = response.getBodyLength(statusBuilder, request, options);
        byte[] body = this.response.getBody(statusBuilder, request, options);

        out.write(statusBuilder.getHTTPStatus());
        out.write(time);
        out.write(location);
        out.write(type);
        out.write(options.getOptions().getBytes());
        out.write(length);
        out.write(body);
        out.flush();
    }
}