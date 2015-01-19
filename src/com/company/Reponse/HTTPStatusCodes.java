package com.company.Reponse;

import com.company.Handler.Routes;

import java.util.Map;
import java.util.HashMap;

public class HTTPStatusCodes {
    
    public String getHTTPStatusCode(String method, String requestPath, String data) {
        String patchString = "PATCH";
        
        Map<String, String> routes = new HashMap<String, String>();
        
        routes.put(Routes.rootRoute(), StatusCodes.twoHundredOk());
        routes.put(Routes.methodOptionsRoute(), StatusCodes.twoHundredOk());
        routes.put(Routes.formRoute(), StatusCodes.twoHundredOk());
        routes.put(Routes.file2Route(), StatusCodes.twoHundredOk());
        routes.put(Routes.logRoute(), StatusCodes.twoHundredOk());
        routes.put(Routes.file1Route(), StatusCodes.fourOhFiveOk());
        routes.put(Routes.textFileRoute(), StatusCodes.forOhFiveNotAllowed());
        routes.put(Routes.redirectRoute(), StatusCodes.threeOhOneMoved());
        routes.put(Routes.partialContentRoute(), StatusCodes.twoOhSixPartial());
        routes.put(Routes.logsRoute(), StatusCodes.fourOhOneUnauthorized());
        routes.put(patchString, StatusCodes.twoOhFourNoContent());
        
        for (Map.Entry<String, String> route : routes.entrySet()) {
            if (requestPath.equals(route.getKey()) || method.equals(route.getKey())) {
                return route.getValue();
            } else if (requestPath.equals(Routes.logsRoute()) && data.equals("localhost:5000")) {
                return StatusCodes.fourOhOneUnauthorized();
            } else if (requestPath.equals(Routes.logsRoute()) && !data.equals("localhost:5000")) {
                return StatusCodes.twoHundredOk();
            }
        }
        return StatusCodes.fourOhFourNotFound();
    }
}