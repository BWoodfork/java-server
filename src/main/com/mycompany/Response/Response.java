package main.com.mycompany.Response;

import main.com.mycompany.Response.Headers.*;
import main.com.mycompany.Response.ResponseBuilders.ResponseBuilder;
import main.com.mycompany.Response.ResponseBuilders.StatusBuilder;
import main.com.mycompany.Routes.Routes;
import main.com.mycompany.Utilities.FileMatcher;
import main.com.mycompany.request.Request;

import java.io.IOException;

public class Response {
    private Routes routes;
    private DateAndTime dateAndTime;
    private ServerLocation serverLocation;
    private ContentType contentType;
    private BodyLength bodyLength;

    public Response() {
        FileMatcher fileMatcher = new FileMatcher();
        routes = new Routes(fileMatcher);
        dateAndTime = new DateAndTime();
        serverLocation = new ServerLocation();
        contentType = new ContentType();
        bodyLength = new BodyLength();
    }
    
    public byte[] getDate() {
        return dateAndTime.getServerTime();
    }
    
    public byte[] getServerLocation(int port) {
        return serverLocation.getLocationResponse(port);
    }
    
    public byte[] getContentType(Request request) throws IOException {
        return contentType.getContentType(request);
    }
    
    public byte[] getBody(StatusBuilder statusBuilder, Request request, Options options) throws Exception {
        ResponseBuilder routeKey = routes.getRequestedRoute(request, statusBuilder);
        
        if (routeKey != null) {
            return routeKey.buildResponse(request, options);
        } else
            statusBuilder.setHTTPStatus(404);
            options.setOptions("GET");
        return "this is not the page you are looking for".getBytes();
    }
    
    public byte[] getBodyLength(StatusBuilder statusBuilder, Request request, Options options) throws Exception {
        return bodyLength.getBodyLength(getBody(statusBuilder, request, options));
    }
}