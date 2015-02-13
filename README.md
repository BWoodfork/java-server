Clone the java-server repository. 
(The Cob Spec repository will also be downloaded with it)

The cob_spec and java-server directory should be in the same directory.

Once the repository is cloned, cd into the cob_spec directory and run 'bundle install'.
 
Now, cd into the java-server directory.

Type 'java -jar out/artifacts/java_server_jar/java-server.jar 5000'
You can enter a port aside from 5000 if you wish.

In the Cob Spec directory, type 'java -jar fitnesse.jar -p 9090'. This will run the Cob Spec test suite.

Open up your browser client and make a request to 'localhost:5000' (alter '5000' with the port you chose)

If you wish to load the Cob Spec test suite, make a request through your browser to 'localhost:9090'. HTTPTestSuite > Suite. Run the suite twice. All tests should be passing. If they are not, make sure cob_spec and java-server directories are in the same directory.