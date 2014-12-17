package com.company.HeaderData;

import com.company.FileRouter;

import java.io.IOException;

public class ServerBody {
    public byte[] getBody(String fullRequest) throws IOException {
        FileRouter fileRouter = new FileRouter();

        return fileRouter.routeFiles(fullRequest);
    }
}
