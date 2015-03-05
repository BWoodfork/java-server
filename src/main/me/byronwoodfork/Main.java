import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) throws IOException {
    String directory = "../cob_spec/public";
    ExecutorService pool = Executors.newCachedThreadPool();
    HTTPStatusCodes httpStatusCodes = new HTTPStatusCodes();
    Routes routes = new Routes(directory);
    Response response = new Response(httpStatusCodes, routes);
    ResponseGenerator responseGenerator = new ResponseGenerator(pool, response, 5000);
    responseGenerator.start();
  }
}