#HTTP Server - Conforms to Cobspec Acceptance Test Suite

By default, this server serves files from the Cobspec/public directory, 
with the intension of passing the Cobspec HTTP Server Tests. Cobspec is an 
acceptance test suite that tests if an HTTP server has the basic components 
needed to run properly. If you wish to use this HTTP Server toserve files 
that are not from the Cobspec test suite, you will need to create a 
`Routes` class that directs to `Responders` and remove the current 
`Routes` class and `Responders package`. 

##Notes:
`Gradle` is required to run tests on console.
You can download Gradle here: [Gradle 2.2.1 Download](https://services.gradle.org/distributions/gradle-2.2.1-all.zip)
After installation, run `gradle` in Console to confirm installation was successful.
You should be presented with a Welcome To Gradle Message.

If you're running Java 7, the tests and server may not run on your system.
To see what version of Java you're running, in Console type java -version

If you're having problems with Java 7, download Java 8 here:
[Java 8 Download](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)

Cobspec and Java-Server directories should be cloned to the same directory.

##To Run Cobspec HTTP Acceptance Test Suite
git clone `https://github.com/8thlight/cob_spec.git`

cd into `cob_spec`
git clone `https://github.com/unclebob/RubySlim`
Ruby Slim is the test suite for Cobspec. Without it, it will not run.

Follow [These Instructions To Setup Cobspec](https://github.com/8thlight/cob_spec)

After you're all set up..
While in the cob_spec directory, type:
`git checkout e47b6ff8051d006dac0d3aa590833f89e819ca2d`

Once one the correct commit, you can run the Cobspec test suite:
`java -jar fitness -p 9090`

##To Run HTTP Server In Console
clone this repository
run `gradle build` from java-server directory
run `java -jar build/libs/java-server-1.0.jar`
The port defaults to port `5000`
The directory defaults to `"../cob_spec/public"`

If you wish to specify a directory and port run:
`java -jar build/libs/java-server-1.0.jar [directory] [port_number]` (w/o brackets)

##To Run In Intellij
Open java-server directory, run `Main`.

##To Build Jar
`gradle build`

##To Run Unit Test Suite
`gradle test`
