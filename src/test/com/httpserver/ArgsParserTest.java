package com.httpserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ArgsParserTest {
  @Test
  public void returnsTrueIfPortIsValid() throws Exception {
    String[] args = {"", "4000"};
    ArgsParser argsParser = new ArgsParser(args);
    assertTrue(argsParser.isAValidPortNumber());
  }
  
  @Test
  public void returnsFalseIfPortIsNotValid() throws Exception {
    String[] args = {"", "-1"};
    ArgsParser argsParser = new ArgsParser(args);
    assertFalse(argsParser.isAValidPortNumber());
  }
  
  @Test
  public void returnsThePortNumber() throws Exception {
    String[] args = {"", "1"};
    ArgsParser argsParser = new ArgsParser(args);
    
    assertEquals(1, argsParser.getPort());
  }
  
  @Test
  public void returnsTheDefaultPortIfPortIsInvalid() throws Exception {
    String[] args = {"", "0"};
    ArgsParser argsParser = new ArgsParser(args);
    assertEquals(5000, argsParser.getPort());
  }
  
  @Test
  public void returnsDefaultPortIfArgIsNull() throws Exception {
    String[] args = {"", null};
    ArgsParser argsParser = new ArgsParser(args);
    assertEquals(5000, argsParser.getPort());
  }
  
  @Test
  public void returnsTheDirectory() throws Exception {
    String[] args = {"some/directory/name", "1"};
    ArgsParser argsParser = new ArgsParser(args);
    assertEquals("some/directory/name", argsParser.getDirectory());
  }
  
  @Test
  public void returnsTheDefaultDirectoryWhenNull() throws Exception {
    String[] args = {null, "1"};
    ArgsParser argsParser = new ArgsParser(args);
    assertEquals(Constants.DEFAULT_SERVER_DIRECTORY, argsParser.getDirectory());
  }
  
  @Test
  public void returnsTrueIfDirectoryIsAValidString() throws Exception {
    String[] args = {"some/directory/name", "1"};
    ArgsParser argsParser = new ArgsParser(args);
    assertTrue(argsParser.isAValidDirectory());
  }
  
  @Test
  public void returnsFalseIfDirectoryIsNotValid() throws Exception {
    String[] args = {null, "1"};
    ArgsParser argsParser = new ArgsParser(args);
    assertFalse(argsParser.isAValidDirectory());
  }
}