import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class PatchContentHandler implements Responder {
  private String directory;
  private static HashMap<String, byte[]> patchMap = new HashMap<String, byte[]>();
  
  public PatchContentHandler(String directory) {
    this.directory = directory;
  }

  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      writeToPatchContent(request, httpStatusCodes);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "Request Invalid".getBytes();
  }
  
  private Path writeToPatchContent(Request request, HTTPStatusCodes httpStatusCodes) throws Exception {
    if (getPatchMap().containsKey(request.getEtag())) {
      httpStatusCodes.setStatus(204);
      return Files.write(getPath(request), getPatchMap().get(request.getEtag()));
    }

    httpStatusCodes.setStatus(200);
    return Files.write(getPath(request), "default content".getBytes());
  }

  private Path getPath(Request request) {
    return Paths.get(directory + "/" + request.getURI()).toAbsolutePath();
  }
  
  private HashMap<String, byte[]> getPatchMap() {
    patchMap.put("60bb224c68b1ed765a0f84d910de58d0beea91c4", "patched content".getBytes());
    patchMap.put("69bc18dc1edc9e1316348b2eaaca9df83898249fC", "default content".getBytes());
    
    return patchMap;
  }
}