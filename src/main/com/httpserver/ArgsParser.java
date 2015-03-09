package com.httpserver;

public class ArgsParser {
  String[] args;

  public ArgsParser(String[] args) {
    this.args = args;
  }
  
  public int getPort() {
    return (isAValidPortNumber()) ? Integer.parseInt(args[1]) : Constants.DEFAULT_PORT;
  }

  public boolean isAValidPortNumber() {
    try {
      return args[1] != null && Integer.parseInt(args[1]) > 0;
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    }
  }

  public String getDirectory() {
    return (isAValidDirectory()) ? args[0] : Constants.DEFAULT_SERVER_DIRECTORY;
  }

  public boolean isAValidDirectory() {
    try {
      return args[0] != null;
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    }
  }
}