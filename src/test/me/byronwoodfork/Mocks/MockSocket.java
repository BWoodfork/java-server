package mocks;

import java.io.*;
import java.net.Socket;

public class MockSocket extends Socket {
  public boolean isListening = true;
  private InputStream inputStream;
  private OutputStream outputStream;
  
  public MockSocket(InputStream inputStream, OutputStream outputStream) {
    this.inputStream = inputStream;
    this.outputStream = outputStream;
  }
  
  @Override
  public InputStream getInputStream() {
    return inputStream;
  }
  
  @Override
  public OutputStream getOutputStream() throws IOException {
    return outputStream;
  }
  
  @Override
  public void close() {
    isListening = false;
  }
  
  @Override
  public boolean isClosed() {
    return isListening;
  }
  
  @Override
  public boolean isConnected() {
    return isListening;
  }
}