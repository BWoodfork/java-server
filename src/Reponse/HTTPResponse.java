package Reponse;

public class HTTPResponse {

    public String getHTTPStatusCode(String method, String requestPath, String data) {
        String[] okPaths = {"/", "/method_options", "/form", "/file2", "/log"};
        String[] unauthorizedPaths = {"/logs"};
        String[] uniqueOkPath = {"/file1"};
        String[] notAllowedPaths = {"/text-file.txt"};
        String[] redirectPath = {"/redirect"};
        String[] partialContentPath = {"/partial_content.txt"};
        String[] noContentPaths = {"/patch-content.txt"};

        for (String pathValue : okPaths) {
            if (pathValue.equals(requestPath)) {
                return "200 OK";
            } else for (String uniquePathValue : uniqueOkPath) {
                if (uniquePathValue.equals(requestPath)) {
                    return "405 OK";
                }
            }

            for (String noContentPathsValue : noContentPaths) {
                if (noContentPathsValue.equals(requestPath) && method.equals("PATCH")) {
                    return "204 No Content";
                } else if (noContentPathsValue.equals(requestPath) && method.equals("GET")) {
                    return "200 OK";
                }
            }

            for (String unauthorizedPathsValue : unauthorizedPaths) {
                if (unauthorizedPathsValue.equals(requestPath) && data.equals("localhost:5000")) {
                    return "401 Unauthorized";
                } else if (unauthorizedPathsValue.equals(requestPath) && data.startsWith("YWR")) {
                    return "200 OK";
                }
            }

            for (String notAllowedPathsValue : notAllowedPaths) {
                if (notAllowedPathsValue.equals(requestPath)) {
                    return "405 Method Not Allowed";
                }
            }

            for (String redirectPathValue : redirectPath) {
                if (redirectPathValue.equals(requestPath)) {
                    return "301 Moved Permanently";
                }
            }

            for (String partialContentPathValue : partialContentPath) {
                if (partialContentPathValue.equals(requestPath)) {
                    return "206 Partial Content";
                }
            }
        }

        return "404 NOT FOUND";
    }
}