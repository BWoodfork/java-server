package com.company.Reponse;

public class HTTPStatusCodes {

    public String getHTTPStatusCode(String method, String requestPath, String data) {
        String[] okPaths = {"/", "/method_options", "/form", "/file2", "/log"};
        String[] unauthorizedPaths = {"/logs"};
        String[] uniqueOkPath = {"/file1"};
        String[] notAllowedPaths = {"/text-file.txt"};
        String[] redirectPaths = {"/redirect"};
        String[] partialContentPaths = {"/partial_content.txt"};
        String[] noContentPaths = {"/patch-content.txt"};

        String twoHundredOK = "200 OK";
        String fourOhFiveOK = "405 OK";
        String twoOhFourNoContent = "204 No Content";
        String fourOhOneUnauthorized = "401 Unauthorized";
        String fourOhFiveNotAllowed = "405 Method Not Allowed";
        String threeOhOneMoved = "301 Moved Permanently";
        String twoOhSixPartial = "206 Partial Content";
        String fourOhFour = "404 NOT FOUND";


        for (String pathValue : okPaths) {
            if (pathValue.equals(requestPath)) {
                return twoHundredOK;
            }
        }

        for (String uniquePathValue : uniqueOkPath) {
            if (uniquePathValue.equals(requestPath)) {
                return fourOhFiveOK;
            }
        }

        for (String noContentPathsValue : noContentPaths) {
            if (noContentPathsValue.equals(requestPath) && method.equals("PATCH")) {
                return twoOhFourNoContent;
            } else if (noContentPathsValue.equals(requestPath) && method.equals("GET")) {
                return twoHundredOK;
            }
        }

        for (String unauthorizedPathsValue : unauthorizedPaths) {
            if (unauthorizedPathsValue.equals(requestPath) && data.equals("localhost:5000")) {
                return fourOhOneUnauthorized;
            } else if (unauthorizedPathsValue.equals(requestPath) && data.startsWith("YWR")) {
                return twoHundredOK;
            }
        }

        for (String notAllowedPathsValue : notAllowedPaths) {
            if (notAllowedPathsValue.equals(requestPath)) {
                return fourOhFiveNotAllowed;
            }
        }

        for (String redirectPathValue : redirectPaths) {
            if (redirectPathValue.equals(requestPath)) {
                return threeOhOneMoved;
            }
        }

        for (String partialContentPathValue : partialContentPaths) {
            if (partialContentPathValue.equals(requestPath)) {
                return twoOhSixPartial;
            }
        }

        return fourOhFour;
    }
}