System requirements
-------------------
All you need to build this project is:
1. Java 8, 
2. Maven 3.5.3, 
3. Wildfly 11 or Jetty 9.4.9.v20180320,
4. MySQL [5.6](https://dev.mysql.com/downloads/mysql/5.6.html) or [5.7](https://dev.mysql.com/downloads/mysql/5.7.html)

The application this project produces is designed to be run on JBoss WildFly and Jetty.


# Working with JBoss
###### 1. Configure Maven
> If you have not yet done so, you must [Configure Maven](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN.md) before testing the quickstarts.

###### 2. Start JBoss WildFly with the Web Profile
- Open a command line and navigate to the root of the JBoss server directory.
- The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
###### 3. Build and Deploy the Quickstart
> _NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](https://github.com/jboss-developer/jboss-eap-quickstarts#build-and-deploy-the-quickstarts) for complete instructions and additional options._

- Make sure you have started the JBoss Server as described above.
- Open a command line and navigate to the root directory of this quickstart.
- Type this command to build and deploy the archive:

        mvn clean package wildfly:deploy

- This will deploy `target/${artifactId}.war` to the running instance of the server.
 

###### 4. Access the application 
The application will be running at the following URL: <http://localhost:8080/${artifactId}/>.


###### 5. Undeploy the Archive
- Make sure you have started the JBoss Server as described above.
- Open a command line and navigate to the root directory of this quickstart.
- When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy



# Working with Jetty
###### 1. Unpack the distribution
- unzip jetty-distribution-9.x

###### 2. Make a {jetty.base} directory to house your configuration
- mkdir myappbase
- cd myappbase

###### 3. Since this is a new {jetty.base}, lets initialize it
- java -jar %JETTY_HOME%/start.jar --add-to-start=http,console-capture,deploy,jsp,ext,resources

        INFO: http            initialised in ${jetty.base}/start.ini (appended)
        INFO: server          initialised in ${jetty.base}/start.ini (appended)
        INFO: logging         initialised in ${jetty.base}/start.ini (appended)
        MKDIR: ${jetty.base}/logs
        INFO: deploy          initialised in ${jetty.base}/start.ini (appended)
        MKDIR: ${jetty.base}/webapps
        ...(snip)...
        MKDIR: ${jetty.base}/lib
        MKDIR: ${jetty.base}/lib/ext
        INFO: resources       initialised in ${jetty.base}/start.ini (appended)
        MKDIR: ${jetty.base}/resources

###### 4. Lets see what we have now
- ls -F
        
        lib/  logs/  resources/  start.ini  webapps/

###### 5. Copy your webapp into place
- cp ~/Projects/mywebapp.war webapps/

###### 6. Run Jetty
- java -jar %JETTY_HOME%/start.jar
