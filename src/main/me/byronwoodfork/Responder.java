public interface Responder {
  public byte[] buildResponse(Request request, HTTPStatusCodes httpStatusCodes);
}