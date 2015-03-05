public class ArgsParser {
  private int port;
  private String directory;
  
  public ArgsParser(int port, String directory) {
    this.port = port;
    this.directory = directory;
  }

  public int getPort() {
    if (port < 0) {
      return 5000;
    }
    
    return port;
  }

  public String getDirectory() {
    return directory;
  }
}