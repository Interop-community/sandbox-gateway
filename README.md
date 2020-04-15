# Logica Sandbox Manager API

Welcome to the Logica Sandbox Gateway!  

# Logica Sandbox

*Note:* If you are wanting to build and test SMART on FHIR Apps, it is recommended that you use the free cloud-hosted version of the HSPC Sandbox.

[Logica Sandbox](https://sandbox.logicahealth.org)

### How do I get set up?
This project uses Java 8. Please make sure that your Project SDK is set to use Java 8.

#### Step 1: Maven Build

In the terminal, run the following command:

    mvn package
    
#### Step 2: Run locally or Run on Docker

###### For local installation
The Sandbox Gateway is not needed to run locally // ASK

    run SandboxgatewayApplication 
###### For Docker Installation

    cd docker
    ./build.sh
    docker-compose up
    
The set up process is now complete and your project is running now. The service is available at: 
    http://localhost:12100

#### Configuration ####

Various property files configure the sandbox gateway:

 * src/main/resources/application.yml
 * src/main/resources/application-*.yml
 
### Where to go from here ###
https://healthservices.atlassian.net/wiki/display/HSPC/Healthcare+Services+Platform+Consortium

## Additional Info

### Port Assignments

| Item                    | Port  |
| ----------------------- | -----:|
| SANDMAN-GATEWAY         | 12100 |
