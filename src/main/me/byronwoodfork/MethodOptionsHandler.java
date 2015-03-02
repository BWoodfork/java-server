public class MethodOptionsHandler implements Responder {
  @Override
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes) {
    try {
      httpStatusCodes.setStatus(200);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return "<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>".getBytes();
  }
}