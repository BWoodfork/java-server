#HTTP Server - Conforms to Cobspec Acceptance Test Suite

By default, this server serves files from the Cobspec/public directory, 
with the intension of passing the Cobspec HTTP Server Tests. Cobspec is an 
acceptance test suite that tests if an HTTP server has the basic components 
needed to run properly. If you wish to use this HTTP Server toserve files 
that are not from the Cobspec test suite, you will need to create a 
`Routes` class that directs to `Responders` and remove the current 
`Routes` class and `Responders package`. 

#Notes:
`Gradle` is required to run tests on console.
You can download Gradle here: [Gradle 2.2.1 Download](https://services.gradle.org/distributions/gradle-2.2.1-all.zip)
After installation, run `gradle` in Console to confirm installation was successful.
You should be presented with a Welcome To Gradle Message.

If you're running Java 7, the tests and server may not run on your system.
To see what version of Java you're running, in Console type java -version

If you're having problems with Java 7, download Java 8 here:
[java 8 download](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

#To Run HTTP Server In Console
clone this repository
run `java -jar build/libs/java-server-1.0.jar` while in java-server directory
The port defaults to port `5000`
The directory defaults to `"../cob_spec/public"`

If you wish to specify a port and directory.
java -jar build/libs/java-server-1.0.jar [directory] [port]

#To Run In Intellij
Open java-server directory, run `Main`.

#To Build Jar
`gradle build`

#To Run Unit Test Suite
`gradle test`

#To Run Cobspec HTTP Acceptance Test Suite
Download 
