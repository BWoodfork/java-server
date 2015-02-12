package main.java.company.Response.ResponseBuilders;

import main.java.company.Response.Headers.Options;
import main.java.company.Utilities.FileMatcher;
import main.java.company.request.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PatchContentResponseBuilderTest {
    private PatchContentResponseBuilder patchContentResponseBuilder;
    private Request patchRequest;
    private StatusBuilder statusBuilder;
    
    @Before
    public void setUp() throws Exception {
        statusBuilder = new StatusBuilder();
        FileMatcher fileMatcher = new FileMatcher();
        patchContentResponseBuilder = new PatchContentResponseBuilder(statusBuilder, fileMatcher);
        patchRequest = new Request("PATCH /patch-content.txt HTTP/1.1Content-Length: 15If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4Connection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded");
    }

    @Test
    public void returnsTrueIfRequestIsAPatchRequest() throws Exception {
        assertEquals(true, patchContentResponseBuilder.isAPatchRequest(patchRequest));
    }

    @Test
    public void returnsFalseIfRequestIsNotAPatchRequest() throws Exception {
        Request request = new Request("GET /patch-content.txt HTTP/1.1Content-Length: 15If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4Connection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded");
        assertEquals(false, patchContentResponseBuilder.isAPatchRequest(request));
    }

    @Test
    public void returnsEtagFromRequest() throws Exception {
        assertEquals("60bb224c68b1ed765a0f84d910de58d0beea91c4" , patchContentResponseBuilder.getEtag(patchRequest));
    }

    @Test
    public void returnsErrorMessageIfNotAPATCHRequest() throws Exception {
        Request request = new Request("POST /patch-content.txt HTTP/1.1Content-Length: 15If-Match: 60bb224c68b1ed765a0f84d910de58d0beea91c4Connection: closeHost: localhost:5000Content-Type: application/x-www-form-urlencoded");
        Options options = new Options();
        assertEquals("The Requested Endpoint Is Not A PATCH Request", new String(patchContentResponseBuilder.buildResponse(request, options)));
    }
    
    @Test
    public void returns204NoContentWhenPatchRequestIsMade() throws Exception {
        Options options = new Options();
        patchContentResponseBuilder.buildResponse(patchRequest, options);
        
        assertEquals("HTTP/1.1 204 No Content\r\n", new String(statusBuilder.getHTTPStatus()));
    }
    
    @Test
    public void returnsPatchInAllowHeaderWhenPatchRequestIsMade() throws Exception {
        Options options = new Options();
        patchContentResponseBuilder.buildResponse(patchRequest, options);
        
        assertEquals("Allow: PATCH\r\n", options.getOptions());
    }
}