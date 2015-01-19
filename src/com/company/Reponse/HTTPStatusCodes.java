package com.company.Reponse;

import com.company.Routes;

import java.util.Map;
import java.util.HashMap;

public class HTTPStatusCodes {
    
    public HashMap<String, String> storedRoutes() {
       HashMap<String, String> storeRoutes = new HashMap<String, String>();
        
        String patchString = "PATCH";
        
        storeRoutes.put(Routes.rootRoute(), StatusCodes.twoHundredOk());
        storeRoutes.put(Routes.methodOptionsRoute(), StatusCodes.twoHundredOk());
        storeRoutes.put(Routes.formRoute(), StatusCodes.twoHundredOk());
        storeRoutes.put(Routes.file2Route(), StatusCodes.twoHundredOk());
        storeRoutes.put(Routes.logRoute(), StatusCodes.twoHundredOk());
        storeRoutes.put(Routes.file1Route(), StatusCodes.fourOhFiveOk());
        storeRoutes.put(Routes.textFileRoute(), StatusCodes.forOhFiveNotAllowed());
        storeRoutes.put(Routes.redirectRoute(), StatusCodes.threeOhOneMoved());
        storeRoutes.put(Routes.partialContentRoute(), StatusCodes.twoOhSixPartial());
        storeRoutes.put(Routes.logsRoute(), StatusCodes.fourOhOneUnauthorized());
        storeRoutes.put(patchString, StatusCodes.twoOhFourNoContent());
        
       return storeRoutes;
    }
    
    public String getHTTPStatusCode(String method, String requestPath, String data) {
        for (Map.Entry<String, String> route : storedRoutes().entrySet()) {
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