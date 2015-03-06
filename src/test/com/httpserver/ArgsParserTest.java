package com.httpserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ArgsParserTest {
  @Test
  public void returns5000AsDefaultPortIfRequestedPortIsANegativeNumber() throws Exception {
    int port = -1;
    String directory = "/some/directory/name";
    ArgsParser argsParser = new ArgsParser(port, directory);
    assertEquals(5000, argsParser.getPort());
  }
  
  @Test
  public void returnsTheRequestedPortIfNumberIsPositive() throws Exception {
    int port = 2000;
    String directory = "/some/directory/name";
    ArgsParser argsParser = new ArgsParser(port, directory);
    assertEquals(2000, argsParser.getPort());
  }

  @Test
  public void returnsTheDirectoryString() throws Exception {
    int port = 2000;
    String directory = "/some/directory/name";
    ArgsParser argsParser = new ArgsParser(port, directory);
    
    assertEquals("/some/directory/name", argsParser.getDirectory());
  }
  
  @Test
  public void returnsDefaultServerDirectoryIfNull() throws Exception {
    int port = 2000;
    String directory = null;
    ArgsParser argsParser = new ArgsParser(port, directory);
    
    assertEquals("../java-server/src/test/cob_spec", argsParser.getDirectory());
  }
}