package main.java.company.Response;

import main.java.company.Response.Headers.*;
import main.java.company.Routes.Routes;
import main.java.company.Utilities.FileMatcher;
import main.java.company.Response.ResponseBuilders.StatusBuilder;
import main.java.company.request.Request;

import java.io.IOException;

public class Response {
    private Routes routes;
    private DateAndTime dateAndTime;
    private ServerLocation serverLocation;
    private ContentType contentType;
    private AllowMethods allowMethods;
    private BodyLength bodyLength;
    private FileMatcher fileMatcher;

    public Response() {
        fileMatcher = new FileMatcher();
        routes = new Routes(fileMatcher);
        dateAndTime = new DateAndTime();
        serverLocation = new ServerLocation();
        contentType = new ContentType();
        allowMethods = new AllowMethods(fileMatcher);
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
        String routeKey = routes.routeKeys(request, statusBuilder);
        
        if (routes.getRoutes(statusBuilder).get(routeKey) != null) {
            return routes.getRoutes(statusBuilder).get(routeKey).buildResponse(request, options);
        } else
            statusBuilder.setHTTPStatus(404);
            options.setOptions("GET");
        return "this is not the page you are looking for".getBytes();
    }
    
    public byte[] getBodyLength(StatusBuilder statusBuilder, Request request, Options options) throws Exception {
        return bodyLength.getBodyLength(getBody(statusBuilder, request, options));
    }
}